from collections import deque

def bfs(graph, start):
    visited = set()
    queue = deque([start])

    visited.add(start)

    while queue:
        node = queue.popleft()
        print(node, end=" ")

        for neighbor in graph[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)


# Example graph
graph = {
    0: [1, 2],
    1: [0, 3, 4],
    2: [0, 5],
    3: [],
    4: [],
    5: []
}

bfs(graph, 0)
