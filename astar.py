from heapq import heappush, heappop

def astar(graph, start, goal, h):
    open_list = [(0, start)]
    g = {start: 0}
    parent = {start: None}

    while open_list:
        _, node = heappop(open_list)

        if node == goal:
            path = []
            while node:
                path.append(node)
                node = parent[node]
            return path[::-1]

        for nei, cost in graph[node]:
            new_g = g[node] + cost

            if nei not in g or new_g < g[nei]:
                g[nei] = new_g
                f = new_g + h[nei]
                heappush(open_list, (f, nei))
                parent[nei] = node

    return None
