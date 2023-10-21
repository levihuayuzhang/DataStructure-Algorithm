/**
 * @file lru.cpp
 * @author Huayu Zhang (zhanghuayu.dev@gmail.com)
 * @brief LRU with unordered map 
 *  https://leetcode.cn/problems/lru-cache/
 *  The map uses for recording the key and value
 *  The double linked list is for changing the "least recent" (order)
 *  The least recent used node is the one at end of the list
 * @version 0.1
 * @date 2023-10-21
 * 
 * @copyright Copyright (c) 2023 Huayu Zhang.
 * 
 */


#include <iostream>
#include <lru.h>

LRUCache::LRUCache(int _capacity): cacapacity(_capacity), size(0) {
    head = new DLinkedNode();
    tail = new DLinkedNode();
    head->next = tail;
    tail->pre = head;

    std::cout << "Cache constructed" << std::endl;
}

int LRUCache::get(int key) {
    if (!cache.count(key)) {
        // std::cerr << "key not found..." << std::endl;
        return -1;
    }

    DLinkedNode* node = cache[key];
    moveToHead(node);
    return node->value;
}

void LRUCache::put(int key, int value) {
    if (!cache.count(key)) { // create new node then record it to map
        DLinkedNode* newNode = new DLinkedNode(key, value);
        cache[key] = newNode;

        addToHead(newNode);

        if (++size > cacapacity) {
            DLinkedNode* removed = removeTail();
            cache.erase(removed->key);
            delete removed; // prevent from mem leak
            --size;
        }
    } else { // update map record and move node to list head (recent used)
        DLinkedNode* node = cache[key];
        node->value = value;
        moveToHead(node);
    }
}

void LRUCache::addToHead(DLinkedNode* node){
    node->next = head->next;
    node->pre = head;
    head->next->pre = node;
    head->next = node;
}

void LRUCache::removeNode(DLinkedNode* node){
    node->pre->next = node->next;
    node->next->pre = node->pre;
}

void LRUCache::moveToHead(DLinkedNode* node){
    removeNode(node);
    addToHead(node);
}

DLinkedNode* LRUCache::removeTail(){
    DLinkedNode* node = tail->pre;
    removeNode(node);
    return node;
}


int main(void) {

    LRUCache* cache = new LRUCache(2);

    cache->put(1, 1);
    cache->put(2, 2);
    std::cout << "Get value: "<< cache->get(1) << std::endl;
    cache->put(3, 3);
    std::cout << "Get value: "<< cache->get(2) << std::endl;
    cache->put(4, 4);

    return 0;
}