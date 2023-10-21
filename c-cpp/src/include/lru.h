/**
 * @file lru.h
 * @author Huayu Zhang (zhanghuayu.dev@gmail.com)
 * @brief LRU with unordered map 
 * https://leetcode.cn/problems/lru-cache/
 * 
 * 
 * @version 0.1
 * @date 2023-10-21
 * 
 * @copyright Copyright (c) 2023 Huayu Zhang
 * 
 */
#ifndef LRU_H
#define LRU_H

#include <unordered_map>

/**
 * node of double linked list
*/
struct DLinkedNode{
    int key, value;
    DLinkedNode* pre;
    DLinkedNode* next;
    DLinkedNode(): key(0), value(0), pre(nullptr), next(nullptr){}
    DLinkedNode(int _key, int _value): key(_key), value(_value), pre(nullptr), next(nullptr){}
};

class LRUCache {
private:
    std::unordered_map<int, DLinkedNode*> cache;
    DLinkedNode* head;
    DLinkedNode* tail;
    int size;
    int cacapacity;

    void addToHead(DLinkedNode*);
    void moveToHead(DLinkedNode*);
    void removeNode(DLinkedNode*);
    DLinkedNode* removeTail();

public:
    LRUCache(int cacapacity);
    int get(int key);
    void put(int key, int value);
};

#endif