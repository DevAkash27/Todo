package com.example.todo


object DataObject {
    var listdata = mutableListOf<CardInfo>()

    fun setData(title: String, priority: String , isDone: Boolean) {
        listdata.add(CardInfo(title, priority , isDone))
    }

    fun getAllData(): List<CardInfo> {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): CardInfo {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos:Int,title:String,priority:String , isDone:Boolean)
    {
        listdata[pos].title=title
        listdata[pos].priority=priority
        listdata[pos].isDone = isDone
    }

}