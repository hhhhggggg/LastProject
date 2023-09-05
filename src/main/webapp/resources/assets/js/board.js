function wrapCreateBoardByMask(){
	var maskHeignt = $(document).height();
	var mastWidth = $(window).width();
	
	$('#mask_board_move').css({'width':maskWidth, 'height':maskHeight});
	
	$('#mask_baord_move').fadeTo("slow", 0.7);
	
	$('.mormal_move_board_modal').show();
}

$(function(){
//기본모달창
$('.openMask_board_move').click(funttion(e){

  e.preventDefault();
  wrapCreateBoardByMask();
});

//닫기 버튼 눌렀을 때
$('.normal_move_board_modal .top .close').click(function(e){
  //링크 기본동작 작동하지 않게 함
  e.preventDefault();
  $('#mask_board_move, .normal_move_board_model').hide();
});

$('#mask_board_move').click(function(){
  $(this).hide();
  $('normal_move_board_modal').hide();
});

});

