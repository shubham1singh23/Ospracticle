def dfs(graph, start):
    visited = set()
    stack = [start]

    while stack:
        node = stack.pop()

        if node not in visited:
            print(node, end=" ")
            visited.add(node)

            # push neighbors
            for neighbor in reversed(graph[node]):
                if neighbor not in visited:
                    stack.append(neighbor)


# Example graph
graph = {
    0: [1, 2],
    1: [0, 3, 4],
    2: [0, 5],
    3: [],
    4: [],
    5: []
}

dfs(graph, 0)
