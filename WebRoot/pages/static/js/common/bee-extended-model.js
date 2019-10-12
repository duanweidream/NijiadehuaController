var BeeModel = function () {

	var $modal = $('#user-modal');
    var initModals = function () {
    	$modal=$('#user-modal');
    }
    
    return {
        //main function to initiate the module
        init: function () {
            // general settings
            $.fn.modal.defaults.spinner = $.fn.modalmanager.defaults.spinner = 
              '<div class="loading-spinner" style="width: 200px; margin-left: -100px;">' +
                '<div class="progress progress-striped active">' +
                  '<div class="progress-bar" style="width: 100%;"></div>' +
                '</div>' +
              '</div>';
            $.fn.modalmanager.defaults.resize = true;
            //ajax demo:
//            var $modal = $('#user-modal');
//            $('#ajax-demo').on('click', function(){
//              // create the backdrop and wait for next modal to be triggered
//              $('body').modalmanager('loading');
//              var el = $(this);
//              setTimeout(function(){
//                  $modal.load(el.attr('data-url'), '', function(){
//                  $modal.modal();
//                });
//              }, 1000);
//            });
//
//            $modal.on('click', '.update', function(){
//              $modal.modal('loading');
//              setTimeout(function(){
//                $modal
//                  .modal('loading')
//                  .find('.modal-body')
//                    .prepend('<div class="alert alert-info fade in">' +
//                      'Updated!<button type="button" class="close" data-dismiss="alert">&times;</button>' +
//                    '</div>');
//              }, 1000);
//            });
        },
        openModel:function(url){
              $('body').modalmanager('loading');
              setTimeout(function(){
        		 //$modal.modal({remote: url,backdrop: 'static', keyboard: false});
                  $modal.load(url, '', function(){
                      $modal.modal({backdrop: 'static', keyboard: false});
                });
              }, 500);
              
              $(function () { 
      			$('#user-modal').on('shown.bs.modal', function () {
      		        //  alert("show.bs.modal");
      				
      				 Page&&Page.validate();
      			})
      		});
        		  
        		$(function () { 
      			$('#user-modal').on('hidden.bs.modal', function () {
      				//alert("hidden.bs.modal");
      				//$(this).removeData("bs.modal");
      			})
      		});
              
              
        },
        closeModel:function(){
       	 $modal.modal('hide');
       }

    };

}();

jQuery(document).ready(function() {    
	BeeModel.init();
});