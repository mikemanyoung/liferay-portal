define("frontend-js-metal-web@1.0.6/metal-modal/src/Modal-min", ["exports","metal/src/metal","metal-dom/src/all/dom","metal-events/src/events","./Modal.soy","metal-jquery-adapter/src/JQueryAdapter"], function(t,e,o,n,i,s){"use strict";function r(t){return t&&t.__esModule?t:{"default":t}}function l(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function a(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e}function u(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)}Object.defineProperty(t,"__esModule",{value:!0});var c=r(e),d=r(o),p=r(i),h=r(s),f=function(t){function e(o){l(this,e);var i=a(this,t.call(this,o));return (i.eventHandler_=new n.EventHandler, i)}return (u(e,t), e.prototype.attached=function(){this.autoFocus_(this.autoFocus)}, e.prototype.autoFocus_=function(t){if(this.inDocument&&this.visible&&t){var e=this.element.querySelector(t);e&&e.focus()}}, e.prototype.detached=function(){t.prototype.detached.call(this),this.eventHandler_.removeAllListeners()}, e.prototype.disposeInternal=function(){d["default"].exitDocument(this.overlayElement),this.unrestrictFocus_(),t.prototype.disposeInternal.call(this)}, e.prototype.handleDocumentFocus_=function(t){this.overlay&&!this.element.contains(t.target)&&this.autoFocus_(".modal-dialog")}, e.prototype.handleKeyup_=function(t){27===t.keyCode&&this.hide()}, e.prototype.hide=function(){this.visible=!1}, e.prototype.restrictFocus_=function(){this.restrictFocusHandle_=d["default"].on(document,"focus",this.handleDocumentFocus_.bind(this),!0)}, e.prototype.shiftFocusBack_=function(){this.lastFocusedElement_&&(this.lastFocusedElement_.focus(),this.lastFocusedElement_=null)}, e.prototype.show=function(){this.visible=!0}, e.prototype.syncHideOnEscape=function(t){t?this.eventHandler_.add(d["default"].on(document,"keyup",this.handleKeyup_.bind(this))):this.eventHandler_.removeAllListeners()}, e.prototype.syncOverlay=function(t){var e=t&&this.visible;d["default"][e?"enterDocument":"exitDocument"](this.overlayElement)}, e.prototype.syncVisible=function(t){this.element.style.display=t?"block":"",this.syncOverlay(this.overlay),this.visible?(this.lastFocusedElement_=document.activeElement,this.autoFocus_(this.autoFocus),this.restrictFocus_()):(this.unrestrictFocus_(),this.shiftFocusBack_())}, e.prototype.unrestrictFocus_=function(){this.restrictFocusHandle_&&this.restrictFocusHandle_.removeListener()}, e.prototype.valueOverlayElementFn_=function(){return d["default"].buildFragment('<div class="modal-backdrop fade in"></div>').firstChild}, e)}(p["default"]);f.prototype.registerMetalComponent&&f.prototype.registerMetalComponent(f,"Modal"),f.ELEMENT_CLASSES="modal",f.ATTRS={autoFocus:{validator:function(t){return t===!1||c["default"].isString(t)},value:".close"},body:{isHtml:!0},footer:{isHtml:!0},header:{isHtml:!0},hideOnEscape:{validator:c["default"].isBoolean,value:!0},overlay:{validator:c["default"].isBoolean,value:!0},overlayElement:{initOnly:!0,valueFn:"valueOverlayElementFn_"},role:{validator:c["default"].isString,value:"dialog"}},t["default"]=f,h["default"].register("modal",f)});