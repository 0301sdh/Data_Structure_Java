# Java Data Structure Study

자료구조를 공부하며 작성한 예제 코드 저장소입니다.

## Progress

- [O] Array
- [O] ArrayList
- [O] LinkedList
- [O] Double LinkedList
- [O] Circular LinkedList
- [O] Stack
- [O] Queue
- [O] Deque
- [O] HashMap
- [O] HashSet

---

## Deque

### 구현 파일

- `MyDeque.java` : 원형 배열 기반 Deque 구현 (제네릭 사용)

### 주요 메서드

| 메서드 | 설명 | 시간복잡도 |
|---|---|---|
| `addFirst(E e)` | 덱 맨 앞에 데이터 삽입 | O(1) (doubling 발생 시 O(n)) |
| `addLast(E e)` | 덱 맨 뒤에 데이터 삽입 | O(1) (doubling 발생 시 O(n)) |
| `removeFirst()` | 맨 앞에서 데이터 꺼냄 | O(1) |
| `removeLast()` | 맨 뒤에서 데이터 꺼냄 | O(1) |
| `peekFirst()` | 맨 앞 데이터 확인 (제거 X) | O(1) |
| `peekLast()` | 맨 뒤 데이터 확인 (제거 X) | O(1) |
| `size()` | 저장된 데이터 수 반환 | O(1) |
| `isEmpty()` | 비어있는지 확인 | O(1) |
| `contains(E e)` | 데이터 검색 | O(n) |
| `toString()` | 데이터 출력 (front → rear) | O(n) |

### Queue와의 차이점

Queue는 뒤에서 넣고 앞에서만 빼지만, Deque은 양쪽 끝에서 삽입/삭제가 모두 가능하다.

```
Queue (단방향):
  삽입 → [A, B, C, D] → 삭제
         rear        front

Deque (양방향):
  삽입/삭제 ← [A, B, C, D] → 삽입/삭제
              front        rear
```

### 왜 `(front - 1 + size) % size`를 사용하는가

Java에서 `-1 % 5`는 수학적 나머지 `4`가 아니라 `-1`을 반환한다.
배열 인덱스에 음수가 들어가면 오류가 발생하기 때문에 `+ size`를 더해서 항상 양수로 만든 뒤 `% size`를 적용한다.

```java
// front가 0일 때
(0 - 1) % 8 = -1        // 배열 인덱스 -1은 오류!
(0 - 1 + 8) % 8 = 7     // 배열 끝으로 정상 이동
```

## Goal

- 자료구조 이해
- Git/GitHub 활용