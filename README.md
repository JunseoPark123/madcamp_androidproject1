앱 기능 요약
============
Tab1 : 연락처
   -
  - 연락처 목록 사전식 정렬
  - 연락처 검색 기능
  - 연락처 추가 기능
  - 연락처 상세 정보 열람
  - 연락처 삭제 및 수정 기능
  - 문자 어플 연동 기능

     
Tab2 : 갤러리
-   
- 사진 그리드뷰 정렬
- 사진 클릭 시 전체화면 로드
- 사진 전체화면 슬라이드뷰
- 휴지통(사진 복구, 완전히 삭제)
- 즐겨찾기 사진 모아보기

  
Tab3 : 토익 단어 공부 
- 
- day 1~30 중 선택한 단어들 중 랜덤으로 10개 퀴즈 제공
- 퀴즈 응시 후 결과 열람 
- 내 단어장 : 틀렸던 문제들 모아보기 기능
  - 단어 열람 및 삭제 가능


상세설명
=====
네비게이션 바 
-
화면 하단에 탭 간 이동을 위한 네비게이션 바가 위치합니다.

현재 탭을 나타내는 아이콘은 파랑색으로 표시되고, 나머지 탭의 아이콘은 회색으로 나타납니다.
![Screenshot 2024-01-03 at 5 09 39 PM](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/99db0d50-3f70-4ee7-a425-0ca256bf1d5a)

Tab1 : 연락처
- 
- 진입화면에 연락처가 사전식 순서로 나타납니다. RecyclerView를 사용해 스크롤이 가능합니다.
- 각 연락처마다 아이콘에 저장명의 첫 글자가 표기됩니다.
- 화면 상단의 검색 바에서 이름을 검색(partial match)할 수 있습니다.
  
 ![Screenshot 2024-01-03 at 5 01 12 PM](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/89ead95e-f0c3-4ef5-aca7-a871923296a3) ![Screenshot 2024-01-03 at 5 01 46 PM](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/4b26f22c-6000-4fae-91d5-6ac787aefefe)



- 우측 상단의 "+" 버튼을 누르면 연락처 추가 화면으로 이동합니다.
  - 이름을 기입하지 않은 상태로 저장하면 경고 메시지를 띄웁니다.
  - 뒤로가기 버튼으로 연락처로 돌아갈 수 있습니다.
  - 이름을 기입한 후 “+” 버튼을 누르면 연락처가 저장됩니다.
  - 저장된 연락처는 자동으로 정렬됩니다.

  ![Screenshot 2024-01-03 at 5 09 39 PM](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/2d4a5f26-d0a4-4e59-b454-d9d0b919baac)

  - 연락처 목록에서 연락처를 클릭하면 상세 정보 열람창으로 이동합니다.
    - 상세 정보 창에서 우측 상단의 저장 버튼을 누르면 연락처가 수정됩니다.
    - 상세 정보 창에서 우측 상단의 삭제 버튼을 누르면 연락처를 삭제할 수 있습니다.
    - 뒤로가기 버튼으로 연락처로 돌아갈 수 있습니다.
    - 저장된 연락처는 자동으로 정렬됩니다.

  ![Screenshot 2024-01-03 at 5 10 03 PM](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/b6f6aa53-ac65-4fd5-b5eb-d4fe597f449b)

- 연락처 리스트 화면에서 전화 버튼을 누르면 전화 기능으로 넘어가 해당 전화번호로 전화를 할 수 있습니다.
- 연락처 리스트 화에서 문자 버튼을 누르면 문자 기능으로 넘어가 해당 전화번호로 문자를 할 수 있습니다.
- 전화번호를 삭제하면 삭제되기 전에 확인/취소 알림창이 뜹니다.
- 전화번호를 추가하면 자동으로 “-” 를 넣어서 저장합니다.

  

https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/73e744d8-3513-4ad1-b768-b8842c156bb9


Tab2 : 갤러리
- 
- 진입화면에 사진들이 GridView로 정렬되어 나타납니다. 화면을 스크롤 할 수 있습니다.
- 화면 우측 상단에 모든 사진, 휴지통, 즐겨찾기 총 3개의 탭 이동 버튼이 있습니다. (진입 화면은 모든 사진입니다.) 현재 탭 아이콘은 검정색으로, 나머지 탭 아이콘들은 옅은 회색으로 나타납니다.
- 휴지통, 즐겨찾기 탭은 사진이 없을 때 각각 “휴지통이 비었습니다”/”즐겨찾기 사진이 없습니다” 텍스트가 뜹니다.

![Screenshot_20240103-182417_madcamp_androidproject](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/4cd04718-77c3-4832-8fee-217497631f5b) ![Screenshot_20240103-182450_madcamp_androidproject](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/a92de51b-fe73-4167-998e-354866e363e9) ![Screenshot_20240103-182455_madcamp_androidproject](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/2ccbebdb-11ef-4bdc-8262-5360062233b4)


- 그리드뷰에서 사진을 클릭하면 해당 사진의 전체화면이 뜹니다. 슬라이드 동작으로 옆 사진으로 이동할 수 있습니다. 백 버튼을 누르면 탭 화면으로 돌아갑니다.
- 모든 사진, 즐겨찾기 탭에서 사진을 클릭하면 전체화면 하단에 즐겨찾기 체크 버튼, 삭제 버튼이 있습니다.
- 하트모양 즐겨찾기 체크 버튼을 눌러서 버튼 색깔을 투명/빨강으로 전환할 수 있습니다. 즐겨찾기 버튼이 빨강색으로 채워지면 즐겨찾기 탭에서도 해당 사진을 볼 수 있습니다.
- 삭제 버튼을 누르면 Confirm 팝업창이 뜨면서 배경이 어두워지고, 확인을 누르면 사진이 휴지통 탭으로 이동하며 취소를 누르면 팝업창이 꺼지며 배경색이 돌아옵니다.


![Screenshot_20240103-182432_madcamp_androidproject](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/1b9aa157-8e0c-415c-b772-f26d5d102a3c) ![Screenshot_20240103-182505_madcamp_androidproject](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/8119320b-e1bd-408e-aa64-37f9de60639b) ![Screenshot_20240103-182510_madcamp_androidproject](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/6a1279be-8943-4440-980e-225f4611884f) ![Uploading Screenshot_20240103-182538_madcamp_androidproject.jpg…]()

- 휴지통 탭에서 사진을 클릭하면 전체화면 하단에 복구, 삭제 버튼이 있습니다. 복구를 하면 모든 사진 탭으로 사진이 이동하며, 삭제하면 휴지통에서 사진이 사라집니다.
- 복구, 삭제 버튼을 누르면 Confirm 팝업창이 뜨면서 배경이 어두워지고, 확인을 누르면 복구/삭제가 진행되며 취소를 누르면 팝업창이 꺼지며 배경색이 돌아옵니다.

![Screenshot_20240103-182531_madcamp_androidproject](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/6392abd0-3c35-4dd1-bc70-450285227119) ![Screenshot_20240103-182545_madcamp_androidproject](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/2a22843b-7cbf-470d-b6a1-2c8ee4572cb0) ![Screenshot_20240103-182549_madcamp_androidproject](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/5b0a66da-f9f7-4e9b-a21d-054ec8c27b46) ![Untitled](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/a54a1412-2806-4617-8d89-1ea3945307f0)

![스크린샷 2024-01-03 183723](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/994f0f96-0b5a-4dd8-b054-a7adde6cb8f7) ![스크린샷 2024-01-03 183838](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/efa39529-388b-4345-9c68-99d43d73078a)





Tab3 : 토익 단어 공부
-
- 진입화면에서 단어 퀴즈/내 단어장 메뉴를 선택할 수 있습니다.

![Untitled](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/92fe396a-e698-441f-8e3f-a48b4e2dd200)

- 단어 퀴즈를 클릭하면 day를 선택할 수 있는 창으로 이동합니다.
    - scroll view를 이용해 day를 선택할 수 있도록 구현했습니다.
- day 선택 후 이동하면 그 day에 해당하는 quiz로 이동합니다.
    - 답에 해당하는 텍스트를 클릭하면 파란색 테두리가 생기고 submit 버튼을 누르면 답이 제출됩니다.
    - 맞았을 경우에는 그림과 같이 초록색으로 버튼이 뜹니다.
    - 틀렸을 경우에는 그림과 같이 빨간색으로 버튼이 뜹니다.
    - 그 후 move to next quiz 버튼을 누르면 다음 문제로 넘어가고 progressbar가 업데이트 됩니다.

![스크린샷 2024-01-03 185338](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/63ab3ea1-efb7-459d-a308-3ca5e3f995c1) ![스크린샷 2024-01-03 183517](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/dec06780-cf10-4894-a354-7384ab07ba85) 

- 10문제를 모두 해결하면 결과에 대한 activity로 넘어가서 day 몇 인지, 맞은 문제, 틀린 문제가 무엇인지를 알려줍니다.

  ![스크린샷 2024-01-03 183941](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/137a4507-0cb1-417c-88ae-fec626da673c)

- 틀린 단어는 내 단어장에서 확인할 수 있으며, 휴지통 버튼을 클릭하면 삭제됩니다.

  ![스크린샷 2024-01-03 183941](https://github.com/JunseoPark123/madcamp_androidproject1/assets/102137004/3806308a-68ef-47b9-a115-568dc33984c7)



  

