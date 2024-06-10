  $(document).ready(function() {
            function setActionUrl() {
                var authorId = $('#author').val();
                console.log('author data',authorId)
                var form = $('#articleForm');
                var action = form.attr('action','/addArticle/authorId/'+authorId);
                
                console.log('author',action)

            }

            // Call the function initially to set the action if the form is pre-filled
            setActionUrl();

            // Call the function whenever the author selection changes
            $('#author').change(function() {
                setActionUrl();
            });
            
            
            
            $('#bannerInput').change(function() {
                var input = this;
                if (input.files && input.files[0]) {
                	$('#bannerImage').removeAttr('hidden');
                    var reader = new FileReader();
                    reader.onload = function(e) {
                        $('#bannerImage').attr('src', e.target.result);
                    };
                    reader.readAsDataURL(input.files[0]);
                }
            });
        });