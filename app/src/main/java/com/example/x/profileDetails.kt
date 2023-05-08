package com.example.x

class profileDetails {
    var fullName:String?=null
    var age:String?=null
    var gender:String?=null
    var phoneNumber:String?=null
    var district:String?=null
    var aadharNumber:String?=null
    var address:String?=null

    constructor(){}

    constructor(name:String?, age:String?,gender:String?,phone:String?,district:String?, aadhar:String?,address:String?){
        this.fullName=name
        this.age=age
        this.gender=gender
        this.phoneNumber=phone
        this.district=district
        this.aadharNumber=aadhar
        this.address=address

    }


}