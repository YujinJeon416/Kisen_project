---

# 1. PREVIEW & 개요

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6dccc039-9ee0-4530-a08f-1de6aa21cdb6/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6dccc039-9ee0-4530-a08f-1de6aa21cdb6/Untitled.png)

  

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8994ebc1-afad-4486-a882-7b317656230b/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8994ebc1-afad-4486-a882-7b317656230b/Untitled.png)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/98748e18-8e43-410b-a91a-ff89e23aa516/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/98748e18-8e43-410b-a91a-ff89e23aa516/Untitled.png)

  

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2f172ce3-ce84-45b5-af92-e7fea10a2e6d/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2f172ce3-ce84-45b5-af92-e7fea10a2e6d/Untitled.png)

[https://youtu.be/jg3SRJwyh5Q](https://youtu.be/jg3SRJwyh5Q)

KH 정보 교육원의 웹 개발자 양성 과정 중 2021년 5월 17일부터 2021년 7월 8일까지 진행되었던 파이널 프로젝트의 결과물로 `아이돌 공식상품 스토어, 비공식상품 스토어, 아티스트별 게시판, 리뷰게시판, 마이페이지, 장바구니, 결제기능 등이 포함되어있는 아티스트 상품 쇼핑몰 사이트`입니다.

# 2. 프로젝트 요구사항

1. 아이돌의 공식굿즈와 비공식굿즈를 통합한 쇼핑몰을 구현하는 것을 목표로 하였다. 아티스트별로 팬게시판을 제공하여 팬들끼리의 커뮤니티 기능도 제공합니다. 
2. 관리자, 기획사 페이지와 사용자 페이지를 각각 따로 만들어 운용의 효율성을 높이고 사용자가 관리자, 기획사 페이지에 접근하는 것을 막습니다. 
3. 사용자는 가입을 해야 모든 서비스 이용이 가능합니다. 
4. 일반 팬들도 비공식굿즈를 만들어 판매가 가능합니다.
5. 웹페이지 디자인은 보라색을 메인컬러로 최대한 깔끔하고 유저친화적으로 디자인합니다.

# 3. 역할 및 담당 기능

### 역할

`프로젝트 팀원`

### 담당 기능

`비공식 굿즈 페이지의 모든 기능 구현`
수요조사 폼 목록보기

입금폼 목록보기

수요조사 폼등록

입금폼 등록

입금폼 수정하기

입금폼 삭제하기

수요조사 폼 상세보기

입금폼 상세보기

수요조사 폼 제출하기

입금폼 제출하기

입금폼 제출시 메일 보내기

# 4. 개발 목표 및 설계 주안점

1. `KI-SEN : K-pop굿즈 쇼핑몰`
K-pop의 Identity를 Sense있게 실현합니다. 
2. `소비자, 아티스트와 굿즈`
소비자는 굿즈 상품 구매를 통해 자신의 취향을 표현하고 아티스트는 자신의 색깔을 표현하는 차별화 전략으로 굿즈를 이용합니다.
3. `관리자 페이지의 다각도 현황 보기 및 관리를 통한 운영의 편의성 제공`
관리자 페이지에서 상품 등록, 수정, 삭제, 판매 상태 관리, 아티스트 정보 관리, 결제, 배송, 회원 관리, 매출 관리를 할 수 있어 운영의 편의성을 제공합니다. 
4. `아티스트 페이지에서 다양한 아티스트의 정보 보기 가능`

    아티스트 페이지에서 아티스트의 뮤직비디오와 관련 상품, 이미지, 팬게시판을 볼 수 있고 상품을 구매할 수 있습니다.

5. `마이페이지에서 나의 주문내역과 배송조회, 관심아티스트 보기가 가능`

    마이페이지에서 개인정보수정, 회원탈퇴, 관심 아티스트보기, 만든 폼 조회, 결제와 주문내역, 배송조회가 가능하여 이용의 편의성을 제공합니다.

# 5. 핵심 구현 기술

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ceed38d1-17a0-48bc-9a76-fbdee5924f68/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ceed38d1-17a0-48bc-9a76-fbdee5924f68/Untitled.png)

**화면설명:**

비공식굿즈 메인페이지입니다. 수요조사폼 만들기 버튼과 입금폼 만들기 버튼, 수요조사폼 목록보기 버튼과 입금폼 목록보기 버튼이 있습니다. 

**구현기능설명**

부트스트랩을 사용하여 버튼과 폼을 만들었습니다. 반응형으로 웹사이트 크기를 줄여도 깨지지 않도록 구현하였습니다. 

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b789a77c-aad0-4d67-85df-1209a9370daa/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b789a77c-aad0-4d67-85df-1209a9370daa/Untitled.png)

**화면설명:**

수요조사폼과 입금폼 만들기 페이지 입니다. 부트스트랩으로 만들었으며 반응형으로 크기를 줄여도 화면비율이 깨지지 않습니다. 

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/aac81df2-6481-420f-96fe-ef8b0d9e5804/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/aac81df2-6481-420f-96fe-ef8b0d9e5804/Untitled.png)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9e5316f2-2cb0-4b0b-be97-98683f24d561/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9e5316f2-2cb0-4b0b-be97-98683f24d561/Untitled.png)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/95726be8-5eab-441d-beca-62861a70f583/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/95726be8-5eab-441d-beca-62861a70f583/Untitled.png)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b1d4bab3-4b6f-4ea2-be0e-886cd6d74072/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b1d4bab3-4b6f-4ea2-be0e-886cd6d74072/Untitled.png)

**구현기능설명**

부트스트랩으로 만든 수요조사폼과 입금폼으로 이미지를 넣을 수 있다는 것이 특징입니다.  이미지 삽입은 POST방식으로 맵에 담아 처리하였습니다. 대표이미지와 상세이미지로 나누어 저장할 수 있도록 구현하였습니다. 수요조사 폼을 작성 후 제출 시 update를 통해 판매량이 1씩 증가하도록 구현하였습니다. 입금폼 제출시 이메일로 구매상품 정보를 구글 email API를 통해 구매자의 이메일로 보낼 수 있도록 구현하였습니다. 

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/40bb08ca-ab6d-4959-a852-5f553ba5289d/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/40bb08ca-ab6d-4959-a852-5f553ba5289d/Untitled.png)

**화면설명:**

마이페이지에서 내가 만든 폼을 볼 수 있습니다. 수정하기와 삭제하기 버튼이 있어서 수정과 삭제가 가능합니다. 

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cce9432a-b93d-420c-a946-c9b484b1473e/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cce9432a-b93d-420c-a946-c9b484b1473e/Untitled.png)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e59eed46-d965-4974-bfcc-ba2041fe2c65/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e59eed46-d965-4974-bfcc-ba2041fe2c65/Untitled.png)

**구현기능설명**

마이페이지에서 FanNo변수를 통해 내가 만든 폼을 불러올 수 있도록 구현하였습니다. 

수정하기는 update를 통해 구현하였고 delete를 통해 삭제하기를 구현하였습니다. 수정하기를 하면 db상에서도 수정이 됩니다. 실수로 삭제를 누를 경우를 대비하여 삭제를 누르면 alert창으로 정말 삭제할 것인지 확인하고 삭제할 수 있도록 구현을 하였습니다. 삭제할 경우 db에서도 완전히 삭제가 됩니다.  

# 6. 구현 기간

### 프로젝트 기획 기간

`2021.05.17 ~ 2021.06.03`

### 프로젝트 구현 기간

`2021.06.03 ~ 2021.07.08`

# 7. 사용 언어 및 도구

O/S : Windows 10

Server: Apache Tomcat V9.0
개발 언어: Java(JSP), Oracle 11g XE, HTML5, CSS, JavaScript, SQL, Ajax, jQuery
IDE: Visual Studio Code, Eclipse(2020-06), SQL Developer, STS
라이브러리: cos.jar, JQuery3.6.0, ojdbc6 

API: Google API, summernote, daum API, kakaoPay API, 이니시스 API,  NAVER API, 배송조회API, 카카오톡 채널 톡상담API

# 8. 프로젝트 참여 소감

교육 과정 중 마지막으로 수행하는 한 달 간의 긴 프로젝트였습니다.

세미 프로젝트를 함께한 팀원들과 두 명의 새로운 팀원이 함께하게 되었습니다. 처음부터 팀웍이 너무 좋았고 기획단계도 재미있게 회의하며 진행되었습니다. 하지만 갑작스러운 팀장님의 취업으로 한명이 빠진채로 프로젝트를 시작하게 되었고 사실 조금 걱정이 되었습니다. 그러나 팀원들이 다들 열심히 해주어서 팀장님의 빈 자리를 채워주어 많이 힘이 되었습니다. 프로젝트를 수행할 때 매일 구글 미트를 통해 작업 상황을 주고받으며 모르는 것을 그때그때 물어보며 해결했던 시간들이 가장 기억에 남습니다. 각자 할 일이 많은 상황에서도 팀원의 문제를 해결해주기 위해 자신의 일처럼 생각해주었던 팀원들에게 너무 고마웠습니다. 2대 팀장님 께서도 취업이 되셨으나 끝까지 프로젝트를 마무리해주시고 가셔서 너무 감사했습니다. 끝까지 오류를 해결하며 포기하지 않았던 팀원들에게도 감사했습니다. 여러모로 협업의 중요성에 대해 많이 느꼈던 프로젝트였습니다. 

특히 이번 프로젝트에서는 깃헙을 이용하여 처음으로 협업을 했는데 초반엔 실수도 많았지만 익숙해지니 너무나 편리하고 좋은 협업 툴 이었습니다. 깃헙에 대해서도 많이 배운 시간이었습니다. 

프로젝트 결과물도 생각한 대로 완성되어서 뿌듯하게 수료식을 할 수 있었습니다.   

# 9. 별첨

[YujinJeon416/Kisen_project](https://github.com/YujinJeon416/Kisen_project)
