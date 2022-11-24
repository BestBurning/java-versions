var peHoverImg = (function(){

  "use strict";

  var bg;

  var initialize = function (options, cb) {

    var config = {
      elems: '.rk-item--hover',
      bgSrc: null,
      transition: '.3s',
      meta: false,
      reverse: false,
      bgColor: 'white'
    };

    for(var prop in options) {
      if(options.hasOwnProperty(prop)){
        config[prop] = options[prop];
      }
    }

    var sheet = (function() {
    	var style = document.createElement('style');
    	style.appendChild(document.createTextNode(''));
    	document.head.appendChild(style);

    	return style.sheet;
    })();

    sheet.insertRule(config.elems + ' { background-size: cover; }', 0);
    sheet.insertRule(config.elems + ' img { transition: opacity ' + config.transition + '; outline: 1px solid ' + config.bgColor + '; }', 1);

    if (config.reverse) {
      sheet.insertRule(config.elems + ' img { opacity: 0; }', 2);
      sheet.insertRule(config.elems + ':hover img { opacity: 1; }', 3);
    } else {
      sheet.insertRule(config.elems + ' img { opacity: 1; }', 2);
      sheet.insertRule(config.elems + ':hover img { opacity: 0; }', 3);
    }

    sheet.insertRule('.pe-img-added { position: absolute; top:50%; left:50%; transform: translate(-50%, -50%); -webkit-transform: translate(-50%, -50%); }', 4);
    sheet.insertRule(config.elems + ':hover { background-color: transparent; }', 5);

    bg = config.elems.constructor === Array ? config.elems.map(function(e){ return document.querySelector(e); }) : document.querySelectorAll(config.elems);

    for (var i = 0; i < bg.length; i++) {

      (function (i) {

        if (bg[i] !== null) {

          if (bg[i].dataset.bgSrc || config.bgSrc) {

            if ( !(bg[i].dataset.meta || config.meta) ) {
              bg[i].querySelector('.item-meta').style.display = 'none';
            }

            if (bg[i].dataset.imgSrc) {
              bg[i].innerHTML = bg[i].innerHTML + '<img class="pe-img-added" src="' + bg[i].dataset.imgSrc + '">';
            }

            var bgs = bg[i].dataset.bgSrc ? bg[i].dataset.bgSrc : config.bgSrc;

            if (bgs !== 'origin') {
              // bg[i].style.backgroundImage = "url(" + bgs + ")";
              var img = new Image();
              img.onload = function() {
                bg[i].style.backgroundImage = "url(" + bgs + ")";
                if(typeof cb == "function" ) {
                  cb();
                }
              };

              img.src = bgs;

            }

          }

        }

      })(i); // loop closure

    } // for loop

  }; // Init()

  return {
    init: initialize
  };

});
