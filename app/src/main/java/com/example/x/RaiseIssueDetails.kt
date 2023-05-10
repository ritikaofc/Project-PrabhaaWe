package com.example.x

class RaiseIssueDetails {
    var issueName:String?=null
    var issueDescription:String?=null
    var tenure:String?=null
    var fundRequired:String?=null
    var supportingDocument:String?=null
    var UPI:String?=null
    var accountDetails:String?=null

    constructor(){}

    constructor(issueName:String?, issueDescription:String?,fundRequired:String?,tenure:String?,supportingDocument:String?, UPI:String?,accountDetails:String?){
        this.issueName=issueName
        this.issueDescription=issueDescription
        this.fundRequired=fundRequired
        this.tenure=tenure
        this.supportingDocument=supportingDocument
        this.UPI=UPI
        this.accountDetails=accountDetails
    }

}