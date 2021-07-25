> 개인적으로 어려웠던 스프링의 원리과 사용기술을 학습하기 위하여 작성한 글입니다. 많은 내용을 참고하여 내용을 정리하였습니다. **수정사항이 있다면 언제든 지적해주세요!!**

## 🔥 개요

- 스프링이란? 스프링 프레임워크는 자바 진영의 웹 프레임워크이다. EJB라는 겨울을 넘어 새로운 시작이라는 뜻으로 시작된 스프링은 현재는 다양한 생태계를 구축하고 있다.
- 개발자로 활동하며 스프링을 사용하는 입장에서 스프링이 제공하고 있는 다양한 기능을 당연하게 사용해왔지만, 정작 스프링을 왜 사용하는지? 사용되는 기술이 무엇인지? 핵심원리를 파악하지 못하고 스프링을 사용해왔다.
- 그래서 스프링 프레임워크는 왜 만들어졌는지, 핵심개념은 무엇인지 알아보고 그 내용을 정리하기 위하여 이 글을 작성한다.

> 스프링은 **자바** 언어 기반의 프레임워크이고, 자바는 **객체 지향 언어**이다.
> 그렇다면 좋은 객체 지향 프로그래밍이란 무엇일까?

#### 객체 지향 프로그래밍(OOP)

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

---

## 🛠 프로젝트 생성

> 프로젝트는 본인의 환경에 맞추어 설정해도 무관하다. 스프링 관련 기술을 학습하기 위한 목적으로 프로젝트를 생성하였으며 불필요한 개발요소는 제외하였다.

#### 사전 준비물

- Java 11 설치
- IntelliJ 설치
- [스프링 프로젝트 생성](https://start.spring.io/)

- 프로젝트 선택
  - Project: Gradle Project
  - Spring Boot: 2.3.x
  - Language: Java
  - Packaging: Jar
  - Java: 11
- Project Metadata
  - groupId: hello
  - artifactId: core
- Dependencies: 선택하지 않는다.

> 기존 자바 코드로 구현하고 관련 상황을 재현하기 위해 스프링 관련 Dependencies를 추가하지 않는다.

#### 비즈니스 요구사항과 설계

- 회원
  - 회원을 가입하고 조회할 수 있다.
  - 회원은 일반과 VIP 두 가지 등급이 있다.
  - 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)
- 주문과 할인 정책
  - 회원은 상품을 주문할 수 있다.
  - 회원 등급에 따라 할인 정책을 적용할 수 있다.
  - 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수 있다.)
  - 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수도 있다. (미확정)

> 현재 미확정 요소가 존재하고 변경될 수 있는 요소가 존재한다. 하지만 우리는 개발일정에 따라 개발을 진행해야 한다. 우리는 인터페이스를 이용하고 구현내용은 언제든 변경이 가능하도록 설계할 것이다.

#### 회원 도메인 개발

- 회원을 가입하고 조회할 수 있다.

```java
public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
```

---

- 회원은 일반과 VIP 두 가지 등급이 있다.

```java
public enum Grade {
    BASIC,
    VIP
}
```

---

- 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)

```java
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
```

> 현재 DB가 정해지지 않아 회원은 메모리 저장소를 이용하여 우선 개발을 진행한다.

#### 주문과 할인 정책

- 회원은 상품을 주문할 수 있다.

```java
public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
```

- 회원 등급에 따라 할인 정책을 적용할 수 있다.

```java
public interface DiscountPolicy {
    int discount(Member member, int price);
}
```

- 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수 있다.)

```java
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;   //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
```

- 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수도 있다. (미확정)

> 우리는 `DiscountPolicy` 인터페이스를 설계한 뒤 `FixDiscountPolicy` 라는 구현체를 통하여 할인 정책을 구현한다. 나중에 할인 정책이 변경되더라도 구현체만 새로 개발하여 교체하기만 하면 된다.

#### 테스트

- 회원 테스트

```java
public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
```

- 주문 테스트

```java
public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
```

#### 중간정리

- 현재 우리는 스프링이 아닌 자바 코드만을 이용하여 요구사항을 개발해왔다. 그렇다면 지금 코드의 문제점은 무엇일까?
- 요구사항이 변경된다면 코드의 수정이 필요하다. `MemoryMemberRepository` -> `JdbcMemberRepository` 로 변경이 필요하다면 객체를 생성하는 부분의 코드 수정이 필요하다.

```java
//MemberRepository memberRepository = new MemoryMemberRepository();
MemberRepository memberRepository = new JdbcMemberRepository();
```

- 지금은 코드의 문제점은 인터페이스와 구현체를 모두 의존하고 있다는 점이다. 즉, 요구사항이 변경될 경우에는 직접 코드를 수정해야 한다.
- 우리가 좋은 객체 지향설계를 하였다면 인터페이스에만 의존하게 개발하고, 요구사항에 맞추어 구현체만 개발하여 갈아끼우면 된다.

#### 새로운 할인 정책 추가

- 그렇다면 `FixDiscountPolicy` 고정 할인 정책을 `RateDiscountPolicy` 정률 할인 정책으로 변경해보도록 하자. 우리는 인터페이스 설계에 따라 구현체만 개발하면 된다.

```java
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
```

- 새로운 할인 정책을 개발하였고 우리는 코드부분을 **수정**해야 한다.

```java
public class OrderServiceImpl implements OrderService {
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
}
```

#### 문제점

- 코드를 수정해야한다는 것은 그것을 의존하고 있다는 뜻이다. `OrderServiceImpl`은 추상(인터페이스) 뿐만 아니라 구체(구현) 클래스에도 의존하고 있다.

> 추상(인터페이스) 의존: `DiscountPolicy`  
> 구체(구현) 클래스: `FixDiscountPolicy` , `RateDiscountPolicy`

- 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다. 따라서 *OCP*를 위반한다. 우리는 인터페이스에만 의존하도록 수정해야 한다.

```java
public class OrderServiceImpl implements OrderService {
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private DiscountPolicy discountPolicy;
}
```

- 위 코드에서는 `DiscountPolicy` 인터페이스만 의존하도록 수정하였다. 하지만 지금상태에서는 구현체가 존재하지 않기때문에 _null pointer exception_ 이 발생할 것이다.

- 이 문제를 해결하기 위해서는 외부에서 구현객체를 대신 생성하고 주입해주어야 한다.(**중요**)

#### 설정클래스 생성

- 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스를 만들자.

```java
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
}
```

- `MemberServiceImpl` 생성자를 이용하여 의존관계 주입

```java
private final MemberRepository memberRepository;

public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
}
```

- `MemberServiceImpl` 의 코드에서는 `MemberRepository` 인터페이스에만 의존하고 있다. 구현객체는 `AppConfig` 에서 `MemoryMemberRepository` 생성하고 의존관계를 주입하고 있다.
- 우리는 `AppConfig`를 통하여 구현객체를 주입하고 관리하도록 코드를 수정했다. `MemberServiceImpl` 에서 구현객체를 직접 생성하여 사용하지 않고 외부에서 의존관계를 주입받아서 사용하게 된다. 어떤 의존성이 주입되는지 모르더라도 우리는 코드 실행에만 충실하면 된다.
- 구현객체가 변경되더라도 `AppConfig` 에서 관리하는 부분만 수정하면 된다. `AppConfig`의 등장으로 애플리케이션이 크게 사용 영역과, 객체를 생성하고 구성(Configuration)하는 영역으로 분리되었다.

- 이제 새로운 할인 정책을 적용해보자

```java
public class AppConfig {

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(),
                discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
```

- `AppConfig` 에서 `RateDiscountPolicy` 구현객체를 바꾸어 주면 된다. 코드를 실행하는 영역과 구성을 책임지는 역할을 구분하였기 떄문에 애플리케이션 확장과 변경이 용이해졌다.
- 실행부분의 소스 코드를 수정하지 않고 설정만 변경하면 되기 때문이다.

#### ❗️SOILD 적용하여 정리

- SRP 단일 책임 원칙

  - 기존에는 구현 객체를 생성, 연결, 실행하는 등의 다양한 책임이 존재했다. 우리는 `AppConfig` 를 통하여 구현 객체를 생성하고 연결하도록 만들었고, 다른 클라이언트 객체는 실행만을 담당하도록 만들었다. 이렇게 하나의 클래스는 하나의 책임만 가지도록 수정하였다.

- DIP 의존관계 역전 원칙

  - 추상화에 의존해야하며 구체화에 의존하면 안된다.
  - 기존에 인터페이스와 구현객체 모두 의존도록 개발되어 있었지만 `AppConfig`를 생성하고 의존관계를 주입하게 변경하여 DIP 문제를 해결하였다.

- OCP 개방-폐쇄 원칙
  - "소프트웨어 요소는 확장에는 열려있으나 변경에는 닫혀 있어야 한다." 이 문구가 굉장히 헷갈렸는데 어느정도 문구를 이해할 수 있었다.
  - `AppConfig`가 의존관계를 설정하여 클라이언트 코드에 주입하기 때문에 클라이언트 코드를 변경하지 않아도 된다.
  - 따라서 새로운 요소를 개발하여도 사용 영역의 변경은 닫혀 있다.

## ⭐️ 정리하며...

- 나는 스프링이 왜 필요한지 궁금했고, 핵심개념이 무엇인지 알고싶었다. 자바의 좋은 객체 지향 원칙을 준수하기 위해 사용된 기술이 지금의 스프링 컨테이너, 빈, 의존관계 등을 만들었다고 생각한다. 추상화(인터페이스), 구현체(클래스)를 이용하여 우리는 유연하고 변경이 용이하게 애플리케이션을 개발할 수 있다.
- 기존 자바 코드를 이용하여 프로젝트를 진행하였다. 스프링 관련 기술을 사용하지 않고 애플리케이션을 개발하려고 보니 좋은 객체 지향 설계를 위반하는 경우가 많이 발생하였다. 내용을 정리하며 이해되었다고 생각했지만 다시 글을 작성하는 과정에서 헷갈리기도 했고 내용이 떠오르지 않기도 했다.
- 예제로 작성한 코드는 어려운 코드는 아니다. 자바를 사용한다면 누구나 이해할 수 있는 수준의 코드라고 생각한다. 하지만 여기서 우리에게 필요한 개념과 앞으로 학습할 스프링의 핵심개념을 알 수 있었다. `AppConfig`처럼 의존관계를 설정하는 역할을 스프링에서는 **DI컨테이너** 라고 한다. **DI컨테이너가** 왜 필요한지, 무슨 역할을 하는지 이해할 수 있었다. 이제 기존 자바코드에서 스프링으로 전환하며 개념을 익히고 역할에 대하여 학습하도록 할 것이다.

> **학습에 도움이 되었던 자료**
>
> - [김영한 - 스프링 핵심 원리(인프런)](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B8%B0%EB%B3%B8%ED%8E%B8#)
> - [객체 지향 프로그래밍(위키백과)](https://ko.wikipedia.org/wiki/%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D)
> - [SOLID(위키백과)](<https://ko.wikipedia.org/wiki/SOLID_(%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%EC%84%A4%EA%B3%84)>)
> - [객체 지향 프로그래밍(위키백과)](https://ko.wikipedia.org/wiki/%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D)
> - [스프링 프로젝트 생성](https://start.spring.io/)

## 🛠 스프링 기반으로 변경

- [이전 글 - 스프링 기초와 원리를 알아보자🌳](https://velog.io/@shlee327/%EC%8A%A4%ED%94%84%EB%A7%81-%EA%B8%B0%EC%B4%88%EC%99%80-%EC%9B%90%EB%A6%AC%EB%A5%BC-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90)
- 이전에 작성된 자바 기반 코드에서 스프링 기반의 코드로 변경하려 한다. 이를 통하여 스프링 컨테이너, 스프링 빈의 사용방법을 익히려 한다.

#### 스프링 컨테이너 생성

```java
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
```

- `@Configuration`, `@Bean`을 추가
- `AppConfig`에 설정을 구성한다는 뜻의 `@Configuration` 을 붙여준다.
- 각 메서드에 `@Bean` 을 붙여준다. 이렇게 하면 스프링 컨테이너에 스프링 빈으로 등록한다.
- 우리는 스프링 컨테이너에서 스프링 빈으로 등록된 객체를 불러와 사용할 것이다.

```java
ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
```

- 스프링 빈은 `@Bean` 이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다.
- `AppConfig` 에서 스프링 빈으로 등록된 `memberService`을 스프링 컨테이너에서 `getBean` 메소드를 통해 필요한 스프링 빈을 찾을 수 있다.
- 기존에는 개발자가 직접 자바 코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경되었다.

> 오히려 코드가 약간 더 복잡해진 것 같은데, 스프링 컨테이너를 사용하면 어떤 장점이 있을까? 아직은 잘 모르겠다...

```java
ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
```

- `ApplicationContext` 는 인터페이스이며 `AnnotationConfigApplicationContext` 는 구현체이다. AnnotationConfig 기반의 설정 파일을 읽어 스프링 컨테이너에 등록하는 역할을 한다.
- 스프링 컨테이너를 생성할 때는 구성 정보를 지정해주어야 한다. 여기서는 AppConfig.class 를 구성 정보로 지정했다.

```java
ApplicationContext ac = new GenericXmlApplicationContext(appConfig.xml);
```

- 이러한 설계 덕분에 스프링은 다양한 설정파일을 등록할 수 있다. 예를들면 `GenericXmlApplicationContext` 는 xml 설정파일을 스프링 컨테이너에 등록하는 역할을 한다.

> 참고 : 정확히는 스프링 컨테이너를 부를 때 BeanFactory, ApplicationContext 로 구분해서 이야기 한다. BeanFactory 를 직접 사용하는 경우는 거의 없으므로 일반적으로 ApplicationContext 를 스프링 컨테이너라 한다.

## ✅ 테스트

#### 컨테이너에 등록된 모든 빈 조회

```java
class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        //스프링에 등록된 모든 빈 이름을 조회한다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            //빈 이름으로 빈 객체(인스턴스)를 조회한다.
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        //스프링에 등록된 모든 빈 이름을 조회한다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
            //ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                //빈 이름으로 빈 객체(인스턴스)를 조회한다.
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}
```

#### 스프링 빈 조회 - 기본

- `ac.getBean(빈이름, 타입)`
- `ac.getBean(타입)`
- 조회 대상 스프링 빈이 없으면 예외 발생 - `NoSuchBeanDefinitionException`

```java
class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX() {
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}
```

#### 스프링 빈 조회 - 동일한 타입이 둘 이상

- 타입으로 조회시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. 이때는 빈 이름을 지정하자.
- `ac.getBeansOfType()` 을 사용하면 해당 타입의 모든 빈을 조회할 수 있다.

```java
class ApplicationContextSameBeanFindTest {

    //특별한 케이스를 지정하기 위해 SameBeanConfig 임시 적용
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
```

#### 스프링 빈 조회 - 상속 관계

- 부모 타입으로 조회하면, 자식 타입도 함께 조회한다.
- 그래서 모든 자바 객체의 최고 부모인 Object 타입으로 조회하면, 모든 스프링 빈을 조회한다.

```java
public class ApplicationContextExtendsFindTest {

    //특별한 케이스를 지정하기 위해 TestConfig 임시 적용
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회하기")
    void findBeanBySubType() {
        FixDiscountPolicy bean = ac.getBean(FixDiscountPolicy.class);
        assertThat(bean).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }

        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
```

## ⭐️ 정리하며...

- 기존의 자바코드 기반 설정정보를 스프링 컨테이너에 스프링 빈으로 등록하는 작업을 시작으로 스프링 컨테이너를 테스트 해봤다.
- `@Configuration`, `@Bean` 은 어노테이션 기반의 자바설정 정보를 등록할 때 사용한다. 따라서 컨테이너에 설정 정보를 등록할 때 `AnnotationConfigApplicationContext` 구현객체를 사용했다.
- 만약 xml 형식의 설정 정보를 등록하려면 `GenericXmlApplicationContext` 구현객체를 이용하면 된다. 이처럼 스프링에서는 다양한 설정 형식을 지원하고 있다. 이것이 가능한 이유는?
- `BeanDefinition` 을 빈 설정 메타정보라 한다. 스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈을 생성한다. `BeanDefinition` 은 추상화(인터페이스) 되어있다. 따라서 다양한 형식의 구현객체를 통하여 설정을 읽을 수 있다.
- `AnnotationConfigApplicationContext`는 `AnnotatedBeanDefinitionReader`를 이용하여 `AppConfig.class` 를 읽고 `BeanDefinition` 을 생성한다.

![](https://images.velog.io/images/shlee327/post/e329faca-9d2f-46e8-a0d9-2e1ae8bb1eee/core_%E2%80%93_AnnotationConfigApplicationContext_java__Gradle__org_springframework_spring-context_5_3_8_-2.png)

- 실제로 `BeanDefinition` 를 이용하여 개발하는 일은 적다고 하지만 동작원리에 대하여 알고 있다면 스프링 관련 오픈소스를 볼 때 원리를 이해할 수 있을 것이다.

> **학습에 도움이 되었던 자료**
>
> - [김영한 - 스프링 핵심 원리(인프런)](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B8%B0%EB%B3%B8%ED%8E%B8#)
