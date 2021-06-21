package com.simpson.kisen.member.controller;

import java.beans.PropertyEditor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpson.kisen.agency.model.vo.Agency;
import com.simpson.kisen.fan.model.vo.Fan;
import com.simpson.kisen.member.model.KakaoProfile;
import com.simpson.kisen.member.model.OAuthToken;
import com.simpson.kisen.member.model.KakaoProfile.KakaoAccount.Profile;
import com.simpson.kisen.member.model.service.MemberService;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
@SessionAttributes({"loginMember", "kakaoMember"})
public class MemberController {
   
   private String pwdKey = "pwd1234";
   
   @Autowired
   private MemberService memberService;
   
   // ��ȣȭ ó��
   @Autowired
   private BCryptPasswordEncoder bcryptPasswordEncoder;
   
   @GetMapping("/login.do")
   // @RequestHeader�� ���� Referer�� ������, referer�� ���� ��츦 ����� required�� false�� ����
   public void memberLogin() {
   }
   
   @GetMapping("/signupTerm.do")
   public void signupTerm() {}
   
   @GetMapping("/signup.do")
   public void signup() {}

   @GetMapping("/signupAgency.do")
   public void signupAgency() {}
   
   @GetMapping("/searchId.do")
   public void searchId() {}
   
   @GetMapping("/searchPwd.do")
   public void searchPwd() {}
   
   
   /**
    * ���̵� �ߺ��˻�
    */
   @GetMapping("/checkIdDuplicate.do")
   // ResponseEntity���� ó�����ֱ� ������ responseBody �ʿ����
   // ResponseEntity�� ��ҷ� Map�� ����
   public ResponseEntity<Map<String, Object>> checkIdDuplicate3(@RequestParam String id) {
      // 1. ��������
      // �� ���̵�� ����ȸ���� �ִ°� Ȯ��
      Fan member = memberService.selectOneMember(id);
      // member�� null������ ���θ� ������ ��Ƶ� (null�̾�� true)
      boolean available = member == null ;
      
      // 2. map�� ��� ���� �� ����
      // model�ʿ� ����
      Map<String, Object> map = new HashMap<>();
      map.put("available", available);
      map.put("id", id);

      // ResponseEntity��ü�� ���� ����
      return ResponseEntity
            .ok() // ������� 200��
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE) // "application/json;charset=UTF-8" -> header������ json�̶�� ���� �˸�
            .body(map); // body�� map���
   }
   
   /**
    * java.sql.Date, java.util.Date �ʵ忡 �����Խ�
    * ����� �Է°��� ""�� ���, null�� ó���� �� �ֵ��� ����
    * @param binder
    */
   // initBinder - Ŀ�ǵ� ��ü ���� ������ ���
   @InitBinder
   public void initBinder(WebDataBinder binder) {
      // [Ư�� Ÿ�Կ� ���� ����ȯ���ִ� editor�� ���]
      // 1. editor���� �ʿ��� ���� ����
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      // 2. editor��ü ����
      // CustomDateEditor(DateFormat dateFormat, boolean allowEmpty)
      // allowEmpty���θ� true�� �ٲٱ� : ���ڿ��� ������ ���� �����
      PropertyEditor editor = new CustomDateEditor(format, true);
      // 3. binder�� editor ���
      // ����ȯ�� �ʿ��ϴٸ� �� editor�� ����϶�
      binder.registerCustomEditor(Date.class, editor);
   }
   
   
   /**
    * ȸ������ ó��
    */
   @PostMapping("/signup.do")
   public String memberEnroll(
         @ModelAttribute Fan member,
         @RequestParam String selectEmail,
         @RequestParam String addressExt1,
         @RequestParam String addressExt2,
         @RequestParam String addressExt3,
         RedirectAttributes redirectAttr
         ) {
      log.info("member = {}", member);
      try {
         member.setAddress(member.getAddress() + ") " + addressExt1 + addressExt2 + " " + addressExt3);
         // member�� ��� �ּ� �ٽ� ����
         member.setAddress(member.getAddress() + ") " + addressExt1 + addressExt2 + " " + addressExt3);
         // member�� ���ÿ��ο� ���� �̸��� ����
         if("1".equals(selectEmail) == false) {
            member.setEmail(member.getEmail() + "@" + selectEmail);
         }
         
         // 0. ��й�ȣ ��ȣȭó��
         String rawPassword = member.getPassword();
         String encodedPassword = bcryptPasswordEncoder.encode(rawPassword);
         // member�� ��ȣȭ�� ��й�ȣ �ٽ� ����
         member.setPassword(encodedPassword);
         
         log.info("member(��ȣȭó�� ����) = {}", member);
         // 1. ��������
         int result = memberService.insertMember(member);
         // 2. ������ǵ�� �� �����̷�Ʈ
         redirectAttr.addFlashAttribute("msg", "ȸ�����Լ���");
         // redirect:/ - �ε���������(welcome file)�� �̵�
         // welcome file�� �ٷ� ã�� �Ǹ� redirectAttr�� ó���� �� ����
      } catch (Exception e) {
         log.error("ȸ������ ����!", e);
         throw e;
      }
      return "redirect:/";
   }
   
   @PostMapping("/signupAgency.do")
   public String memberAgencyEnroll(
         @ModelAttribute Fan member,
         @RequestParam String selectEmail,
         @RequestParam String addressExt1,
         @RequestParam String addressExt2,
         @RequestParam String addressExt3,
         @ModelAttribute Agency agency,
         @RequestParam String fanNoExt1,
         @RequestParam String fanNoExt2,
         RedirectAttributes redirectAttr
         ) {
      log.info("member = {}", member);
      try {
         // member�� ��� �ּ� �ٽ� ����
         member.setAddress(member.getAddress() + ") " + addressExt1 + addressExt2 + " " + addressExt3);
         // member�� ���ÿ��ο� ���� �̸��� ����
         if("1".equals(selectEmail) == false) {
            member.setEmail(member.getEmail() + "@" + selectEmail);
         }
         
         // agency�� ��� ����ڹ�ȣ �ٽ� ����
         member.setFanNo("agcy_" + agency.getFanNo() + fanNoExt1 + fanNoExt2);
         
         // 0. ��й�ȣ ��ȣȭó��
         String rawPassword = member.getPassword();
         String encodedPassword = bcryptPasswordEncoder.encode(rawPassword);
         // member�� ��ȣȭ�� ��й�ȣ �ٽ� ����
         member.setPassword(encodedPassword);
         log.info("member(��ȣȭó�� ����) = {}", member);
         // 1. ��������
         // 1.1. fan���̺� ����
         int result = memberService.insertMemberAgency(member, agency);
         // 2. ������ǵ�� �� �����̷�Ʈ
         redirectAttr.addFlashAttribute("msg", "��ȹ�� ȸ�����Լ���");
         // redirect:/ - �ε���������(welcome file)�� �̵�
         // welcome file�� �ٷ� ã�� �Ǹ� redirectAttr�� ó���� �� ����
      } catch (Exception e) {
         log.error("ȸ������ ����!", e);
         throw e;
      }
      return "redirect:/";
   }
   
   // ���2. handler mapping���� security ������ ����� authentication ��û�ϱ�
   @PostMapping("/loginProcess.do")
   public void memberTest(Authentication authentication, Model model) {
      Fan principal = (Fan) authentication.getPrincipal();
      model.addAttribute("loginMember", principal);
      
      log.info("authentication = {}", authentication);
      // authentication = org.springframework.security.authentication.UsernamePasswordAuthenticationToken@23abe407: Principal: Member(id=honggd, password=$2a$10$qHHeJGgQ9teamJyIJFXbyOBtl7nIsQ37VP2jrz89dnDA7LgzS.nYi, name=ī�浿, gender=M, birthday=2021-05-04, email=honggd@naver.com, phone=01012341234, address=����� ������, hobby=[�,  ���], enrollDate=2021-05-20, authorities=[ROLE_USER], enabled=true); Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@166c8: RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId: B95C1041773474D93729781512D4490A; Granted Authorities: ROLE_USER
      log.info("principal = {}", principal);
   }
   

   @GetMapping("/kakao/callback")
   public String kakaoCallback(@RequestParam String code, Model model, RedirectAttributes redirectAttr) { // data�� �������ִ� ��Ʈ�ѷ� �Լ�
      // POST������� key=value �����͸� īī�������� ��û
      
      // HttpHeader ������Ʈ ����
      RestTemplate rt = new RestTemplate();
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
      
      // HttpBody ������Ʈ ����
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("grant_type", "authorization_code");
      params.add("client_id", "fd88614f9ea0303ee10198eee2c817e1");
      params.add("redirect_uri", "http://localhost:9090/kisen/member/kakao/callback");
      params.add("code", code); // �޾ƿ� ���� �ڵ� �������� �ֱ�
      
      // HttpHeader�� HttpBody�� �ϳ��� ������Ʈ�� ���
      // body ������(params)�� header��(headers)�� ���� entity �����
      // why? exchange�޼ҵ忡 HttpEntity<?> ������Ʈ�� �־�� �ϱ� ����
      HttpEntity<MultiValueMap<String, String>> kakaoTalkRequest =
            new HttpEntity<>(params, headers);
      
      // Http ��û�ϱ� - POST������� - response������ ������ ����
      ResponseEntity<String> response = rt.exchange(
         "https://kauth.kakao.com/oauth/token", // ��ū�߱� ��û�ּ�
         HttpMethod.POST, // ��û�޼ҵ�
         kakaoTalkRequest, // httpEntity (body, header)
         String.class // response�� ������ string �����ͷ� �� ��!
      );
      
      // json data -> java���� ó���ϱ� ���� java Object�� ��ȯ
      ObjectMapper objectMapper = new ObjectMapper();
      OAuthToken oauthToken = null;
      try {
         oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
      } catch (JsonParseException e) {
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      System.out.println("kakao access token : " + oauthToken.getAccess_token());
      // kakao access token : dvkkT0-g31B9SQTKx9ijLZNKLcThYkRz_7-42Qo9dZwAAAF6Kf0GXA
      
      // HttpHeader ������Ʈ ����
      RestTemplate rt2 = new RestTemplate();
      HttpHeaders headers2 = new HttpHeaders();
      headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
      headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
      
      // HttpHeader�� HttpBody�� �ϳ��� ������Ʈ�� ���
      // body ������(params)�� header��(headers)�� ���� entity �����
      // why? exchange�޼ҵ忡 HttpEntity<?> ������Ʈ�� �־�� �ϱ� ����
      HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
            new HttpEntity<>(headers2);
      
      // Http ��û�ϱ� - POST������� - response������ ������ ����
      ResponseEntity<String> response2 = rt2.exchange(
         "https://kapi.kakao.com/v2/user/me", 
         HttpMethod.POST, // ��û�޼ҵ�
         kakaoProfileRequest2, // httpEntity (body, header)
         String.class // response�� ������ string �����ͷ� �� ��!
      );
      
      ObjectMapper objectMapper2 = new ObjectMapper();
      KakaoProfile kakaoProfile = null;
      try {
         kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
      } catch (JsonParseException e) {
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      // user ������Ʈ : username, password, email
      System.out.println("īī�� ���̵� (��ȣ) : " + kakaoProfile.getId()); // īī�� ���̵� (��ȣ) : 1776027704
      System.out.println("īī�� �̸��� : " + kakaoProfile.getKakao_account().getEmail()); // īī�� �̸��� : dbs7wl7@naver.com
      System.out.println("kisen ���� �������� : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId()); // kisen ���� �������� : dbs7wl7@naver.com_1776027704
      System.out.println("kisen ���� �̸��� : " + kakaoProfile.getKakao_account().getEmail()); // kisen ���� �̸��� : dbs7wl7@naver.com
      // UUID garbagePassword = UUID.randomUUID();
      System.out.println("kisen ���� �н����� : " + pwdKey); // kisen ���� �н����� : 6d3bd309-88cc-4c18-88a0-78fea82e2c50
      
      String gender = (String)kakaoProfile.getKakao_account().getGender();
      
      System.out.println("īī�� �̸�, ����, ��, ������� : " + 
               gender + 
               kakaoProfile.getProperties().getNickname() + 
               kakaoProfile.getKakao_account().getBirthday());
      
      String kakaoBirthday = kakaoProfile.getKakao_account().getBirthday();
      String birth1 = kakaoBirthday.substring(0, 2);
      String birth2 = kakaoBirthday.substring(2, 4);
      String bday = "1900-" + birth1 + "-" + birth2;
      java.sql.Date birthday = java.sql.Date.valueOf(bday);
      
      // user object
      Fan kakaoMember = Fan.builder()
            .fanId(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
            .password(pwdKey)
            .email(kakaoProfile.getKakao_account().getEmail())
            .oauth("kakao")
            .gender(kakaoProfile.getKakao_account().getGender())
            .phone(kakaoProfile.getKakao_account().getPhone_number())
            .fanName(kakaoProfile.getProperties().getNickname())
            .birthday(birthday)
            .build();
      
      // ������ Ȥ�� ������ üũ�ؼ� ó��
      Fan originMember = memberService.selectOneMember(kakaoMember.getFanId());
      if(originMember == null) {
         // ������ -> ȸ������ -> �α���ó��
         log.info("���� ȸ���� �ƴմϴ�. �ڵ� ȸ�������� �����մϴ�.");
         // memberService.insertMember(kakaoMember);
         // model.addAttribute("kakaoMember", kakaoMember);
         redirectAttr.addFlashAttribute("kakaoMember", kakaoMember);
         return "redirect:/member/signup.do";
      } else {
         // ������ -> �α���ó��
         model.addAttribute("kakaoMember", kakaoMember);
         return "/member/login";
      }
   }


}