// 
// http://codepen.io/leonderijke/pen/bkgxi
//

jQuery(function($) {
    $('.js-blink-infinite').modernBlink();
  
    $('.js-blink-5').modernBlink({
      iterationCount: 5
    });
  
    $('.js-blink-manual').modernBlink({
      auto: false
    });
    $('.js-btn-start').on( 'click', function() {
      $('.js-blink-manual').modernBlink('start');
    });
    $('.js-btn-stop').on( 'click', function() {
      $('.js-blink-manual').modernBlink('stop');
    });
  
    $('.js-blink-furiously').modernBlink({
      duration: '300'
    });
});
// 
// http://codepen.io/leonderijke/pen/bkgxi
//