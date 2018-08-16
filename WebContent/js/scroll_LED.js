window.setInterval(scrollit, 3000);

function scrollit() {
//    console.log(($("#scroller").scrollTop() + $("#scroller").innerHeight()))
//    console.log($("#scroller")[0].scrollHeight)
        
    if(($("#scroller").scrollTop() + $("#scroller").innerHeight()) >= $("#scroller")[0].scrollHeight)   
        $('#scroller').animate({ scrollTop: 0 }, 1100).delay(3000);  
    else   
        $('#scroller').animate({ scrollTop: $("#scroller").scrollTop() + 033 }, 'slow',function(){
          
    });
}
