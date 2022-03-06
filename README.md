# DesignPattern_Study_Composit

# Notion Link
https://five-cosmos-fb9.notion.site/Composite-a75d4b4d46f04fe0a7ef3b0bb54737db

# 복합체 (Composite)

### 의도

부분가 전체의 계층을 표현하기 위해 객체들을 모아 트리 구조로 구성한다.

사용자로 하여금 개별 객체와 복합 객체를 모두 동일하게 다룰 수 있도록 하는 패턴

**하나의 오브젝트 인스턴스와 그룹을 같게 취급한다. (in WIKI)**

- Object와 Object들의 Group을 같은 타입으로 취급한다? = ***같은 인터페이스를 가지고 있다.***
- 여기서 같은 인터페이스는 → ***베이스 인터페이스로 표현할 수 있고 이를 Component라 부른다.***
- 이를 상속받는 객체를 **Leaf**라 일컫는다.
- 이를 상속받는 그룹을 **Composite**라 일컫는다.
- Composite 안에는 Object 리스트가 들어가는데 Leaf가 아닌, 베이스 Component 가 들어가게 된다.

<aside>
🎈 두개를 같은 타입으로 바라보는 것 자체가 컴포짓 패턴의 핵심이다. 🙂

</aside>

![image](https://user-images.githubusercontent.com/18654358/156904069-48cfd9f8-07c3-40dc-a5a5-a55026d3a90a.png)


<aside>
🎈 Tree 구조가 아주 복잡할 때 Tree Root에서 함수 하나만 점화 시키면 Composite 패턴을 통해 최말단 Leaf 까지 퍼져나갈 수 있다.

</aside>

![image](https://user-images.githubusercontent.com/18654358/156904076-7983d6d7-7d7e-46d6-a420-4d6f3c9071ea.png)

[Animal.java](http://Animal.java) (interface)

```java
public interface Animal {
    void speak();
}
```

Cat.java, [Dog.java](http://Dog.java) (implements Animal)

```java
public class Cat implements Animal{
    @Override
    public void speak() {
        System.out.println("cat speak!!");
    }
}
//////////////////////////////////////
public class Dog implements Animal{
    @Override
    public void speak() {
        System.out.println("dog speak!!");
    }
}
```

[AnimalGroup.java](http://AnimalGroup.java) (implements Animal)

```java
import java.util.ArrayList;
import java.util.List;

public class AnimalGroup implements Animal{

    private List<Animal> animals;

    public AnimalGroup(){
        this.animals = new ArrayList<>();
    }

    @Override
    public void speak() {
        System.out.println("ANIMAL GROUP SPEAK!");
        animals.forEach(Animal::speak);
    }

    public void add(Animal animal) {
        this.animals.add(animal);
    }
}
```

고양이 그룹과 강아지 그룹을 만들고 이 그룹들을 가지는 동물원을 만들어 speak 해보자!

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("hi");

        AnimalGroup catGroup = new AnimalGroup();
        catGroup.add(new Cat());
        catGroup.add(new Cat());
        catGroup.add(new Cat());

        AnimalGroup dogGroup = new AnimalGroup();
        dogGroup.add(new Dog());
        dogGroup.add(new Dog());

        AnimalGroup zoo = new AnimalGroup();
        zoo.add(catGroup);
        zoo.add(dogGroup);

        zoo.speak();
    }
}
```

```java
C:\Users\ssh1224\.jdks\azul-15.0.5\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2021.3\lib\idea_rt.jar=53612:C:\Program Files\JetBrains\IntelliJ IDEA 2021.3\bin" -Dfile.encoding=UTF-8 -classpath D:\dev\study\DS_Composite\out\production\DS_Composite Main
hi
ANIMAL GROUP SPEAK!
ANIMAL GROUP SPEAK!
cat speak!!
cat speak!!
cat speak!!
ANIMAL GROUP SPEAK!
dog speak!!
dog speak!!

Process finished with exit code 0
```

### 우리 제품에 적용시킬 수 있는 케이스는 무엇이 있을까?

- 조직도 트리를 볼 수 있을 것 같다.
- Node라는 추상적 객체를 구현한 객체로 **MemberNode**, **OrgNode**가 있고
- 이를 묶은 개념인 **OrgTreeNode**를 정의할 수 있을 것 같다.
- OrgTreeNode에는 컬랙션 형태로 Node가 존재하게 된다.
    - OrgTreeNode에는 MemberNode, OrgNode들이 존재하게 될 것이다.

### 활용성

- 부분 - 전체의 객체 계통을 표현하고 싶을 때
- 사용자가 객체의 합성으로 생긴 복합 객체와 개개의 객체 사이의 차이를 알지 않고도 자기 일을 할 수 있도록 만들고 싶을 때
- 복합 구조의 모든 객체를 똑같이 취급하게 된다.

### 구조

![image](https://user-images.githubusercontent.com/18654358/156904082-387d517f-d90b-4038-aa97-b932c9391029.png)

**전형적인 Compiste 객체 구조**

![image](https://user-images.githubusercontent.com/18654358/156904087-086cf030-d565-42a1-b80f-5883f4335167.png)

### 참여자

**Component**

- 객체 관계에 정의될 모든 객체에 대한 인터페이스를 정의
- 모든 클래스에 해당하는 인터페이스에 대해서는 공통의 행동을 구현
- 전체 클래스에 속한 요소들을 관리하는데 필요한 인터페이스를 정의
- 순환 구조에서 요소들을 포함하는 전체 클래스로 접근하는데 필요한 인터페이스를 정의

**Leaf**

- 가장 말단의 객체
- 즉 자식이 없는 객체를 나탙낸다.
- 객체 합성에서 가장 기본이 되는 객체의 행동을 정의

**Composite**

- 자식이 있는 구성요소에 대한 행동의 정의
- 자신이 복합 요소들을 저장하면서 Component 인터페이스에 정의된 자식 관련 연산을 구현

### 협력방법

- 사용자는 복합 구조 내 객체 간의 상호작용을 위해 Component 클래스 인터페이스를 사용한다.
- 요청받은 대상이 Leaf 인스턴스이면 자신이 정의한 행동을 직접 수행하고
- 대상이 Composite 이면 자식 객체들에게 요청을 위임한다.
    - 위임하기 전/후 다른 처리를 수행할 수도 있다.
