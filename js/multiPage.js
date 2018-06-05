/**
	*	@FILE_NAME:	multiPage.js		
	*	@DEVELOPERS: Dean Morton	
	*	@BRIEF_DESCRIPTION:	Deviding a large list into several pages
	*/

var $el = $("#wrap > div");
var pageSize = 4;

$el.slice(0, pageSize).css({background: 'white', display: 'block'});
$el.slice(pageSize, $el.length).css({background: 'white', display: 'none'});

function addSlice(num){
  return num + pageSize;
}

function subtractSlice(num){
  return num - pageSize;
}

var slice = [0, pageSize];

$('.next').click(function(){
   if (slice[1] < $el.length ){ 
     slice = slice.map(addSlice);   
   }
   showSlice(slice);
});

$('.prev').click(function(){
  if (slice[0] > 0 ){ 
    slice = slice.map(subtractSlice); 
  }
  showSlice(slice);
});

function showSlice(slice){
  $el.css('display', 'none');
  $el.slice(slice[0], slice[1]).css('display','block');
}