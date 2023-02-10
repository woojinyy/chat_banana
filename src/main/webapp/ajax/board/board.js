//비동기 통신객체 생성해서 담을 변수 선언
let xhrObject = null;
//비동기 통신객체 생성하는 함수 구현 - 단 리턴예약어가 없어서 반환 받을 수 없다
function createRequest() {
    //자바스크립트에서도 예외 처리가 가능하다
  try {
    xhrObject = new XMLHttpRequest(); //constructor 생성자
  } catch (trymicrosoft) {
    try {
      //MS의 IE사용자 위한 객체 생성
      xhrObject = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (error) {
      xhrObject = null;
    }
  }
  if (xhrObject == null) {
    alert("비동기 통신 객체 생성 에러");
  }
} //end of createRequest
function getBoardSold() {
  console.log("getBoardSold");
  return xhrObject;
}
//span태그가 가지고 있는 텍스트 노드값을 읽어오기
//<td>문자</td>태그이름이 없다-> 속성을 정의 못한다 -> ID(#),class 정의 못한다 = 접근불가 어쾀? 
//=>텍스트 노드를  span 태그써 /div 블럭요소 부분만 바꿀 때 써
//el 값은 document.querySelector("#ID")
//el 값은 document.querySelector(".class")
//el 값은 document.querySelector("태그이름")

//const constEl= document.querySelector("#cost")//구매가
//const priceEl= document.querySelector("#price")//판매가
function getText(el) {// 파라미터 el이 뭔데??????

  let text = "";//ES6 적용 안되는 브라우저라면 자바스크립트 컴파일러 bable이 필요하다  or 번들러(배포하는 버전에 따라 버전변경해줄게!!) parsle,웹팩-리액트
  if (el != null) {
    if (el.childNodes) {//el =null 이면 스킵 undefined 스킵 빈무낮열 스킵 NaN 스킵
      for (let i = 0; i < el.childNodes.length; i++) {//costEl or priceEl
        let childNode = el.childNodes[i];//el.childNodes[0]=???, el.childNodes[1]=??? 몇바뀌 도는거야
        //너 텍스트 노드니?
        if (childNode.nodeValue != null) {
          text = text + childNode.nodeValue; //재할당
        }
      }
    }
  }
  return text;
}
//기존 TextNode의 값을 다른 문자열로 바꾸기
/***********************************************
param1 :document.getElementById("boardSold")
param1 :document.querySelector("#boardSold")
param2 :xhrObject. 
************************************************/
function replaceText(el, value) {//el -> boardSoldEl(보드판매량), cashEl(이윤)
  if (el != null) {//span
    clearText(el); //기존에 있던 10을 지워주세요.
    //새로운 텍스트 노드 15를 생성하기
    let newNode = document.createTextNode(value); //15
    //위에서 생성한 텍스트 노드 값을 el에 붙이는 함수 호출하기
    el.appendChild(newNode);//el = boardSoldEl-> span태그  <span id = boardSold or cash></span> <span>20</span> replaceTxt가 해줌
  }
}
//기존 태그안에 문자열 지우는 함수 구현
function clearText(el) {
  if (el != null) {
    if (el.childNodes) {//자바스크립트에서는 0이아닌 것은 모두 참이다
      for (let i = 0; i < el.childNodes.length; i++) {
        let childNode = el.childNodes[i];
        el.removeChild(childNode);//DOM API 직관적이지 않다,유지보수 어렵다 쓰기싫다
      }
    }
  }
}
