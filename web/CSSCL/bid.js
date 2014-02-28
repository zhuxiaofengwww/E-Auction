function checkForm()
{
    var valid=true;
    var message="Error: \n";
    with(document.bid)
    {
        if((txtUserBidAmount.value=="")||isNaN(txtUserBidAmount.value))
        {
            valid=false;message=message+'bidAmount must be a valid number \n';
        }
    }
    if(!valid)
    {alert(message);}
    return valid;
}
