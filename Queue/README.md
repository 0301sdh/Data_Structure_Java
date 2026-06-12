# Java Data Structure Study

자료구조를 공부하며 작성한 예제 코드 저장소입니다.

## Progress

- [O] Array
- [O] ArrayList
- [O] LinkedList
- [O] Stack
- [O] Queue
- [O] HashMap
- [O] HashSet

---

## Queue

### 구현 파일

- `MyQueue.java` : 원형 배열 기반 Queue 구현

### 주요 메서드

| 메서드 | 설명 | 시간복잡도 |
|---|---|---|
| `enqueue(Object obj)` | 큐 맨 뒤에 데이터 삽입 | O(1) (doubling 발생 시 O(n)) |
| `dequeue()` | 큐 맨 앞에서 데이터 꺼냄 | O(1) |
| `peek()` | 맨 앞 데이터 확인 (제거 X) | O(1) |
| `size()` | 저장된 데이터 수 반환 | O(1) |
| `isEmpty()` | 비어있는지 확인 | O(1) |
| `contains(Object obj)` | 데이터 검색 | O(n) |
| `toString()` | 데이터 출력 (front → rear) | O(n) |

### 왜 `% size`를 사용하는가 (원형 배열)

일반 배열로 Queue를 구현하면 dequeue할 때마다 front가 뒤로 밀리면서 앞쪽 공간이 낭비된다.

```
dequeue 2번 후:
[ null, null, 30, 40, 50 ]
               front        → 앞의 2칸은 다시 쓸 수 없음
```

`% size`를 사용하면 인덱스가 배열 끝에 도달했을 때 0으로 되돌아가면서 빈 공간을 재활용할 수 있다.

```java
this.rear = (this.rear + 1) % this.size;   // rear가 끝에 도달하면 0으로
this.front = (this.front + 1) % this.size; // front도 마찬가지
```

```
enqueue(70) 시:
[ 70, null, 30, 40, 50 ]
      rear  front          → rear가 0으로 돌아와서 빈 공간에 삽입
```

이렇게 배열을 원형으로 순환시키면 enqueue, dequeue 모두 O(1)을 유지할 수 있다.

## Goal

- 자료구조 이해
- Git/GitHub 활용