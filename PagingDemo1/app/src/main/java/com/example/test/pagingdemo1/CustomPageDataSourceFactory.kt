package com.example.test.pagingdemo1

import android.arch.paging.DataSource


class CustomPageDataSourceFactory (val repository: DataRepository): DataSource.Factory<Int, DataBean>(){
    override fun create(): DataSource<Int, DataBean> {
        return CustomPageDataSource(repository)
    }

}