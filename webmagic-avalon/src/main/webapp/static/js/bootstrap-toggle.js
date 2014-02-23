/**
 * bootstrap-toggle.js v1.0
 * http://github.com/Nijikokun/bootstrap-toggle/
 * --
 * http://twitter.com/nijikokun
 * Copyright 2012 Nijiko Yonskai, Goodybag Inc
 * --
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
!function ($) {
  
  var Toggle = function (element, options) {
    var self = this;

    // Collect elements
    this.$element = $(element)
    this.$checkbox = this.$element.children('.checkbox')
    this.options = $.extend({}, $.fn.toggle.defaults, options)

    // Initial Setup from options
    if(this.options.text.enabled) this.$element.attr('data-enabled', this.options.text.enabled)
    if(this.options.text.disabled) this.$element.attr('data-disabled', this.options.text.disabled)      

    // setup state
    this.setState(this.$checkbox.is(':checked'))
    
    // Setup Click
    this.$element.click(function (e) {
      if(self.options.onClick) self.options.onClick(e, self.$checkbox.is(':checked'))
      self.toggle()
    });
  }
  
  Toggle.prototype.setState = function (state) {
    // change checkbox state
    this.$checkbox.attr('checked', state)
    
    if(state) {
      this.$element.removeClass('disabled')
      if(this.options.style.disabled)
        this.$element.removeClass('disabled-' + this.options.style.disabled)
      if(this.options.style.enabled)
        this.$element.addClass(this.options.style.enabled)
    } else {
      this.$element.addClass('disabled')
      if(this.options.style.enabled)
        this.$element.removeClass(this.options.style.enabled)
      if(this.options.style.disabled)
        this.$element.addClass('disabled-' + this.options.style.disabled)
    }
  }
  
  Toggle.prototype.on = function () {
    this.setState(true)
  }
  
  Toggle.prototype.off = function () {
    this.setState(false)
  }
  
  Toggle.prototype.toggle = function () {
    var status = this.$checkbox.is(':checked')

    // Toggle status
    this.setState(!status)
  }
  
  /*
   * Toggle Definition
   */
  $.fn.toggle = function (options) {
    return new Toggle(this, typeof options == 'object' ? options : {})
  }
  
  $.fn.toggle.defaults = {
    onClick: function () {},
    text: {
      enabled: false,
      disabled: false
    },
    style: {
      enabled: false,
      disabled: false
    }
  }
  
  $.fn.toggle.Constructor = Toggle
}(window.jQuery);