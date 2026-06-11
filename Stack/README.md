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
- [O] HashMap
- [O] HashSet

## 2026/06/11 MyStack 작성
Java로 직접 Stack 구현 (배열 기반)
구현한 메소드 : push, pop, peek, contains, size, isEmpty, doubling

## MyStack 시간복잡도
| 메서드      | 시간복잡도 | 설명 |
|--------    |-----------|------|
| `push()` | 평균 O(1), 최악 O(n) | doubling 발생 시 O(n) |
| `pop()` | O(1) | top 위치에서 바로 제거 |
| `peek()` | O(1) | top 위치 데이터 바로 접근 |
| `contains()` | O(n) | 처음부터 순회하며 검색 |
| `size()` | O(1) | top 값 반환 |
| `isEmpty()` | O(1) | top == 0 비교 |

## Goal

- 자료구조 이해
- Git/GitHub 활용