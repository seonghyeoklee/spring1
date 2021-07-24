> 개인적으로 어려웠던 스프링의 원리과 사용기술을 학습하기 위하여 작성한 글입니다. 많은 내용을 참고하여 내용을 정리하였습니다. **수정사항이 있다면 언제든 지적해주세요!!**

## 🔥 개요
- 스프링이란? 스프링 프레임워크는 자바 진영의 웹 프레임워크이다. EJB라는 겨울을 넘어 새로운 시작이라는 뜻으로 시작된 스프링은 현재는 다양한 생태계를 구축하고 있다.
- 개발자로 활동하며 스프링을 사용하는 입장에서 스프링이 제공하고 있는 다양한 기능을 당연하게 사용해왔지만, 정작 스프링을 왜 사용하는지? 사용되는 기술이 무엇인지? 핵심원리를 파악하지 못하고 스프링을 사용해왔다.
- 그래서 스프링 프레임워크는 왜 만들어졌는지, 핵심개념은 무엇인지 알아보고 그 내용을 정리하기 위하여 이 글을 작성한다.

> 스프링은 **자바** 언어 기반의 프레임워크이고, 자바는 **객체 지향 언어**이다.
> 그렇다면 좋은 객체 지향 프로그래밍이란 무엇일까?

### 객체 지향 프로그래밍(OOP)

- *객체 지향 프로그래밍(Object Oriented Programming)*은 컴퓨터 프로그래밍의 패러다임 중 하나이다.
- _OOP_ 는 컴퓨터 프로그램을 명령어의 목록으로 보는 시각에서 벗어나 여러개의 독립된 단위인 **객체**들의 모임으로 파악하고자 하는 것이다.
- 각각의 객체는 메시지를 주고받고 데이터를 처리할 수 있다.
- 객체 지향 프로그래밍은 프로그램을 **유연**하고 **변경**이 용이하게 만들기 때문에 대규모 소프트웨어 개발에 많이 사용된다.
- 참고 - [객체 지향 프로그래밍(위키백과)](https://ko.wikipedia.org/wiki/%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D)

#### 유연하고 변경이 용이하다?

- 그렇다면, 유연하고 변경이 용이하다는 표현은 어떤 뜻일까? 내가 좋아하는 레고는 매우 유연하고 변경이 용이하다. 블럭(객체)을 조립하여 하나의 레고 완성품을 만드는 것처럼, 프로그래밍에서는 컴포넌트를 유연하게 변경하면서 개발하는 것을 의미한다고 생각한다.

#### 핵심개념은 무엇일까?

- 객체 지향 프로그래밍은 추상화, 상속, **다형성** 등이 있으며 추가적으로 다중 상속 등의 특징이 존재한다.
- 그 중 _OOP_ 의 핵심 개념인 **다형성(Polymorphism)** 에 대하여 알아보겠다.
- 유연하고 변경이 용이한 좋은 객체 지향 프로그래밍을 하기 위해서는 다형성이 매우 중요하다. 왜 다형성이 중요할까?

#### 역할과 구현

- 다형성을 쉽게 이해하기 위해 실세계에 비유하여 생각해보자.
- 자동차에 비유하여 생각해보자. 자동차의 종류는 매우 다양하다. 하지만 운전자는 자동차 사용방법에 대하여 알고 있다면 자동차를 운행하는데 아무런 지장이 없다.
- **자동차(역할)**를 **생산(구현)**한 것이라 생각하면 편하다. **생산(구현)**된 자동차는 매우 다양하지만, 그것이 **자동차(역할)**라는 것은 변함이 없다.
- 역할과 구현을 분리하면 세상이 단순해지고, 유연해지며 **변경**이 용이해진다.
- 따라서 **우리(클라이언트)**는 **역할(인터페이스)**만 알고있으면 구현의 구체적인 내용은 몰라도 상관없다. 역할만 알고있다면 우리는 구현내용이 변경되더라도 클라이언트에게 영향을 미치지 않는다. 마치 자동차의 엔진, 변속기, 제어 등등의 구체적인 자동차 내부구조 내용을 모르더라도 클라이언트는 인터페이스만 알고있다면 자동차를 운전하는데 큰 문제는 없을 것이다.

> 역할 - 인터페이스  
> 구현 - 구현클래스

- 객체를 설계할 때 **역할**과 **구현**을 분리하는 것이 매우 중요하다.
- 수 많은 객체 클라이언트와 객체 서버는 서로 협력관계를 가진다.
- 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경할 수 있다.

![](https://images.velog.io/images/shlee327/post/d56aa0da-11e3-4c69-b14d-e4f116e61fcc/3856679-200.png)
## 🌈 SOLID - 좋은 객체 지향 설계의 5가지 원칙

1. **SRP: 단일 책임 원칙(single responsibility principle)**
    - 한 클래스는 하나의 책임만 가져야 한다.
2. **OCP: 개방-폐쇄 원칙 (Open/closed principle)**
    - 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
    - 기능을 추가하려면 소스코드의 수정이 불가피하다. 다형성을 이용하더라도 클라이언트 코드를 변경해야 한다. 이는 OCP 원칙에 위배되는 것이다. 이것을 해결하기 위해서는 객체를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자가 필요하다.
3. **LSP: 리스코프 치환 원칙 (Liskov substitution principle)**
    - 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
    - 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것, 다형성을 지원하기 위 한 원칙, 인터페이스를 구현한 구현체는 믿고 사용하려면, 이 원칙이 필요하다.
4. **ISP: 인터페이스 분리 원칙 (Interface segregation principle)**
    - 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
5. **DIP: 의존관계 역전 원칙 (Dependency inversion principle)**
    - 프로그래머는 “추상화에 의존해야지, 구체화에 의존하면 안된다.” 의존성 주입은 이 원칙 을 따르는 방법 중 하나다. 쉽게 이야기해서 구현 클래스에 의존하지 말고, 인터페이스에 의존하라는 뜻이다.

- 참고 - [SOLID(위키백과)](<https://ko.wikipedia.org/wiki/SOLID_(%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%EC%84%A4%EA%B3%84)>)

#### 정리

- 객체지향의 핵심은 **다형성**이다. 좋은 객체 지향 설계를 위해서는 다형성이 매우 중요하다. 하지만 순수한 자바코드에서 다형성 만으로는 구현 객체를 변경할 때 클라이언트 코드도 함께 변경된다. 즉, 순수한 자바코드로는 **OCP(개방-폐쇄 원칙), DIP(의존관계 역전 원칙)** 를 지킬 수 없다는 뜻이다.
- 순수한 자바코드로 **OCP, DIP** 를 지키며 개발을 진행하면, 결국 스프링이 제공하는 핵심 기술에 근접하게 된다.

## ☀️ 스프링과 다형성

- 우리는 **좋은 객체 지향 원칙(SOLID)**을 알아보았고, 자바 언어의 핵심인 다형성에 대하여 알아보았다. 이것이 스프링과 어떠한 관계가 있는 것일까?
- 스프링은 다형성을 극대화해서 이용할 수 있도록 다음의 기술을 제공한다. 제어의 역전, 스프링 컨테이너를 이용한 의존관계 주입 등은 다형성을 활용하여 역할과 구현을 편리하게 다룰 수 있도록 지원한다.
- 즉, 우리가 앞서 알아본 **객체 지향 설계 원칙(SOLID)**을 지키며 개발할 수 있도록 스프링에서 지원한다는 것이다. **DI(Dependency Injection) 컨테이너**는 클라이언트의 코드 변경없이 기능을 확장하도록 도와준다.
- 이것이 스프링의 등장하게 된 핵심개념이며, 앞으로 스프링이 제공하는 기술은 왜 등장하였는지 알아보고, 소스코드를 통하여 알아보도록 하겠다.(글로만 내용을 이해하긴 어렵기 때문에...)

> 이상적으로는 모든 설계에 **인터페이스**를 부여한다. 하지만 기능을 확장할 가능성이 없다면, 구체 클래스를 직접 사용하고 향후 꼭 필요할 때 리팩터링해서 인터페이스를 도입하는 것도 방법이다.

- 참고 - [객체 지향 프로그래밍(위키백과)](https://ko.wikipedia.org/wiki/%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D)
