# 자바로 직접 구현한 자료구조 (Data Structures in Java)

군 전역 후 개발자를 목표로 자바 기초 체력을 기르기 위해,
**표준 라이브러리에 의존하지 않고 핵심 자료구조를 직접 구현**하며 원리를 정리한 저장소입니다.

단순히 "동작하는 코드"가 아니라 *"왜 이렇게 동작하는가"*, *"어떤 상황에 무엇을 써야 하는가"* 를
이해하는 데 집중했습니다. 각 자료구조는 **직접 구현(`My~`) + 테스트 코드 + 정리 문서(README)** 로 구성되어 있습니다.

<br>

## 학습 원칙

- 내부 동작 원리를 **코드로 직접** 확인하기 (라이브러리 사용 최소화)
- 연산별 **시간복잡도(Big-O)** 를 근거 있게 설명할 수 있기
- 구현마다 **테스트 코드**로 정상 동작을 검증하기
- 폴더별 **README에 개념·비교·시행착오**를 기록하기

<br>

## 구현 목록

> 각 항목을 클릭하면 해당 자료구조의 상세 정리 문서로 이동합니다.

### 선형 자료구조 (Linear)

| 자료구조 | 구현 | 핵심 포인트 |
|----------|------|-------------|
| [**Array**](./Array) | 예제 4종 | 인덱스 임의 접근 O(1), 연속 메모리 |
| [**ArrayList**](./ArrayList) | `MyArrayList` + 테스트 | 동적 배열 확장(resizing), 삽입 amortized O(1) |
| [**LinkedList**](./LinkedList) | 단일 / 이중 / 원형 3종 + 테스트 | 노드 포인터 연결, 삽입·삭제 O(1) |
| [**Stack**](./Stack) | `MyStack` + 테스트 | 후입선출(LIFO) |
| [**Queue**](./Queue) | `MyQueue` + 테스트 | 선입선출(FIFO) |
| [**Deque**](./Deque) | `MyDeque` + 테스트 | 양방향 삽입·삭제 |

### 해시 기반 (Hash)

| 자료구조 | 구현 | 핵심 포인트 |
|----------|------|-------------|
| [**HashMap**](./HashMap) | `MyHashMap`(Chaining) + `MyHashMap2`(Open Addressing) + 테스트 | 두 가지 충돌 해결 방식 비교 구현 |
| [**HashSet**](./HashSet) | `MyHashSet` + 테스트 | 중복 없는 집합, 해시 기반 |

### 트리 · 힙 (Tree / Heap)

| 자료구조 | 구현 | 핵심 포인트 |
|----------|------|-------------|
| [**Tree**](./Tree) | BST · AVL · Red-Black · B-Tree · B+Tree (5종) + 테스트 | 자가 균형 트리 · 디스크 기반 트리까지 |
| [**Heap**](./Heap) | `MaxHeap` | 완전 이진트리, 우선순위 큐의 핵심 |
| [**Trie**](./Trie) | `Trie` + `TrieMap` | 문자열을 문자 단위로 저장 |

### 그래프 (Graph)

| 자료구조 | 구현 | 핵심 포인트 |
|----------|------|-------------|
| [**Graph**](./Graph) | `Graph` (DFS/BFS) | 정점·간선 관계, 탐색 O(V+E) |
| [**DisjointSet**](./DisjointSet) | `DisjointSet` (Union-Find) | 경로 압축 + Union by Rank 최적화 |

<br>

## 특히 공들인 부분

- **Tree (5종)** — 일반 BST의 편향 문제부터 시작해, 이를 해결하는 **AVL·Red-Black** 자가 균형 트리, 그리고 디스크 기반 대용량 처리를 위한 **B-Tree·B+Tree** 까지 단계적으로 구현했습니다. (DB 인덱스가 왜 B+Tree인지까지 정리)
- **HashMap (2종)** — 해시 충돌을 **Chaining**(연결 리스트)과 **Open Addressing**(Linear Probing) 두 방식으로 각각 구현하고, Load Factor·삭제 처리·메모리 사용을 비교했습니다.
- **DisjointSet** — 경로 압축(Path Compression)과 Union by Rank 두 최적화가 시간복잡도를 어떻게 사실상 O(1)로 만드는지 정리했습니다.

<br>

## 기술 스택

- **Language**: Java
- **구성**: 자료구조별 `직접 구현 + 테스트 코드 + 정리 문서(README)`
- **원칙**: 핵심 로직은 표준 컬렉션 라이브러리 없이 직접 구현

<br>

## 프로젝트 구조

```
Data_Structure_Java/
├── Array/          # 배열 기초
├── ArrayList/      # 동적 배열 (MyArrayList)
├── LinkedList/     # 단일 · 이중 · 원형 연결 리스트
├── Stack/          # 스택 (MyStack)
├── Queue/          # 큐 (MyQueue)
├── Deque/          # 덱 (MyDeque)
├── HashMap/        # 해시맵 (Chaining + Open Addressing)
├── HashSet/        # 해시셋 (MyHashSet)
├── Heap/           # 최대 힙 (MaxHeap)
├── Tree/           # BST · AVL · Red-Black · B-Tree · B+Tree
├── Trie/           # 트라이 (Trie, TrieMap)
├── Graph/          # 그래프 (DFS/BFS)
└── DisjointSet/    # 서로소 집합 (Union-Find)
```

각 폴더 안의 `README.md` 에 해당 자료구조의 개념·시간복잡도·시행착오를 정리해두었습니다.
