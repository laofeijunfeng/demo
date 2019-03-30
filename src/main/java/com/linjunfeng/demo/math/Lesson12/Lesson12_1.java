package com.linjunfeng.demo.math.Lesson12;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Lesson12_1 {

    public static void main(String[] args) {
        Node[] user_nodes = beNode(5, 5);
        bfs(user_nodes, 3);
    }

    public static Node[] beNode(int user_num, int relation_num) {
        Random rand = new Random();
        Node[] user_nodes = new Node[user_num];
        for (int i = 0; i < user_num; i++)
            user_nodes[i] = new Node(i);
        for (int i = 0; i < relation_num; i++) {
            int friend_a_id = rand.nextInt(user_num);
            int friend_b_id = rand.nextInt(user_num);
            if (friend_a_id == friend_b_id) continue;
            Node friend_a = user_nodes[friend_a_id];
            Node friend_b = user_nodes[friend_b_id];
            friend_a.friends.add(friend_b_id);
            friend_b.friends.add(friend_a_id);
        }
        return user_nodes;
    }

    /**
     * 通过广度优先搜索，查找好友
     * @param user_nodes 用户的结点；
     * @param user_id 给定用户Id，我们要为这个用户查询好友
     */
    public static void bfs(Node[] user_nodes, int user_id) {
        if (user_id > user_nodes.length) return;
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(user_id);
        HashSet<Integer> visited = new HashSet<>();
        visited.add(user_id);

        while (!queue.isEmpty()) {
            int current_user_id = queue.poll();
            if (user_nodes[current_user_id] == null) continue;
            for(int friend_id : user_nodes[current_user_id].friends) {
                if (user_nodes[friend_id] == null) continue;
                if (visited.contains(friend_id)) continue;
                queue.offer(friend_id);
                visited.add(friend_id);
                user_nodes[friend_id].degree = user_nodes[current_user_id].degree + 1;
                System.out.println(String.format("\t%d 度好友：%d", user_nodes[friend_id].degree, friend_id));
            }
        }
    }

    private static class Node {
        public int user_id;
        public HashSet<Integer> friends;
        public int degree;

        public Node(int id) {
            user_id = id;
            friends = new HashSet<>();
            degree = 0;
        }
    }
}
