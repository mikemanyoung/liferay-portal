define("frontend-js-metal-web@1.0.6/metal-component/src/surfaces/SurfaceCollector-min", ["exports","metal/src/metal"], function(e,t){"use strict";function r(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function n(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var s=function(e){function s(){r(this,s);var t=o(this,e.call(this));return (t.surfaces_={}, t)}return (n(s,e), s.prototype.addSurface=function(e,t){this.surfaces_[e]?this.updateSurface(e,t):(this.surfaces_[e]=t||{},this.surfaces_[e].surfaceElementId=e)}, s.prototype.disposeInternal=function(){this.surfaces_=null}, s.prototype.getSurface=function(e){return this.surfaces_[e]?this.surfaces_[e]:null}, s.prototype.removeAllSurfaces=function(){this.surfaces_=[]}, s.prototype.removeSurface=function(e){this.surfaces_[e]=null}, s.prototype.updateSurface=function(e,r){t.object.mixin(this.surfaces_[e],r)}, s)}(t.Disposable);s.prototype.registerMetalComponent&&s.prototype.registerMetalComponent(s,"SurfaceCollector"),e["default"]=s});