(function(c){var b={pos:[-260,-260]},d=3,h=document,g=h.documentElement,e=h.body,a,i;function f(){if(this===b.elem){b.pos=[-260,-260];b.elem=false;d=3}}c.event.special.mwheelIntent={setup:function(){var j=c(this).bind("mousewheel",c.event.special.mwheelIntent.handler);if(this!==h&&this!==g&&this!==e){j.bind("mouseleave",f)}j=null;return true},teardown:function(){c(this).unbind("mousewheel",c.event.special.mwheelIntent.handler).unbind("mouseleave",f);return true},handler:function(j,k){var l=[j.clientX,j.clientY];if(this===b.elem||Math.abs(b.pos[0]-l[0])>d||Math.abs(b.pos[1]-l[1])>d){b.elem=this;b.pos=l;d=250;clearTimeout(i);i=setTimeout(function(){d=10},200);clearTimeout(a);a=setTimeout(function(){d=3},1500);j=c.extend({},j,{type:"mwheelIntent"});return(c.event.dispatch||c.event.handle).apply(this,arguments)}}};c.fn.extend({mwheelIntent:function(j){return j?this.bind("mwheelIntent",j):this.trigger("mwheelIntent")},unmwheelIntent:function(j){return this.unbind("mwheelIntent",j)}});c(function(){e=h.body;c(h).bind("mwheelIntent.mwheelIntentDefault",c.noop)})})(jQuery);