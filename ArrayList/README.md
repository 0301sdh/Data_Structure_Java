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

## 2026/06/08 MyArrayList 작성
Java로 직접 ArrayList 구현
구현한 메소드 : add, get, remove, insert, contains. size. isEmpty, doubling

## MyArrayList 시간복잡도 
| 메서드      | 시간복잡도 | 설명 |
|--------    |-----------|------|
| `add()` | 평균 O(1), 최악 O(n) | doubling 발생 시 O(n) |
| `get()` | O(1) | 배열 인덱스로 바로 접근 |
| `remove()` | O(n) | 삭제 후 데이터 이동 |
| `insert()` | O(n) | 삽입 위치 이후 데이터 이동 |
| `contains()` | O(n) | 처음부터 순회하며 검색 |
| `size()` | O(1) | index 값 반환 |
| `isEmpty()` | O(1) | index == 0 비교 |

## Goal

- 자료구조 이해
- Git/GitHub 활용