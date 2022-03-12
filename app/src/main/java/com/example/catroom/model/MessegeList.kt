package com.example.catroom.model

class MessegeList {
    var messegeId:String?=null
    var senderId:String?=null
    constructor(){}
    constructor(messegeId:String?,senderId:String?){
        this.messegeId=messegeId
        this.senderId=senderId
    }
}