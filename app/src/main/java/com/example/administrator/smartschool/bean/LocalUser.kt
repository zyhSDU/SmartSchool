package com.example.administrator.smartschool.bean

import com.ab.db.orm.annotation.Column
import com.ab.db.orm.annotation.Id
import com.ab.db.orm.annotation.Table

/**
 * Created by Administrator on 2018/6/8 0008.
 */

@Table(name = "local_user")
class LocalUser {

    // ID @Id主键,int类型,数据库建表时此字段会设为自增长
    @Id
    @Column(name = "_id")
    var _id: Int = 0

    @Column(name = "u_id")
    private var uId: String? = null

    // 登录用户名 length=20数据字段的长度是20
    @Column(name = "name", length = 20)
    var name: String? = null

    // 用户密码
    @Column(name = "password")
    var password: String? = null

    // 年龄一般是数值,用type = "INTEGER"规范一下.
    @Column(name = "age", type = "INTEGER")
    var age: Int = 0

    // 创建时间
    @Column(name = "create_time")
    var createTime: String? = null

    // 结束时间
    @Column(name = "end_time")
    var endTime: String? = null

//    // 包含实体的存储，指定外键
//    @Relations(name = "stock", type = "one2one", foreignKey = "u_id", action = "query_insert")
//    var stock: Stock? = null
//
//    // 包含List的存储，指定外键
//    @Relations(name = "stocks", type = "one2many", foreignKey = "u_id", action = "query_insert")
//    var stocks: List<Stock>? = null

    // 有些字段您可能不希望保存到数据库中,不用@Column注释就不会映射到数据库.
    @Column(name="remark")
    var remark: String? = null

    override fun toString(): String {
        return "LocalUser(_id=$_id, uId=$uId, name=$name, password=$password, age=$age, createTime=$createTime, endTime=$endTime, remark=$remark)"
    }


}
