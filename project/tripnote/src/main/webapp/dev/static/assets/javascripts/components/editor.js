'use strict'

App.AceEditorComponent = Ember.Component.extend({
  random: Math.floor(Math.random() * 10000),
    content: Ember.computed({
        get: function() {
            if(!this.editor) {
                return undefined;
            }
            if(arguments.length == 1) {
                return this.editor.getSession().getValue();
            }
            return undefined;
        },
        set: function(key, val) {
            if(!this.editor) {
                this.preset = val;
                return val;
            }
            var cursor = this.editor.getCursorPosition();
            this.editor.getSession().setValue(val);
            this.editor.moveCursorToPosition(cursor);
            return val;
        }
    }),
  didInsertElement: function() {
    this.editor = window.ace.edit(this.random + '_editor');
    this.editor.$blockScrolling = Infinity;
    this.editor.setTheme('ace/theme/github');
    this.editor.getSession().setMode('ace/mode/sql');
    this.editor.setReadOnly(false);
    var self = this;
    this.editor.on('change', function(){
      self.notifyPropertyChange('content');
    });

    if(this.preset) {
      this.set('content', this.preset);
      this.preset = null;
    }
  },
  actions: {
    save: function(){
      var url = this.get('savePath');
      url = url.replace(/\{.\w+\}/, this.get('itemId'))
      var method = 'POST';
      var token = $("#csrfToken").val().replace('/', '');
      var data = {"csrfToken": token};
      data[this.get('saveParam')] = this.editor.getSession().getValue()
      $.ajax({
        url: url,
        method: method,
        headers: {
          'Csrf-Token': token
        },
        dataType: 'json',
        data: data
      }).done(function(data, txt, xhr){
        if(data && data.status && data.status == "success")
          {
            console.log('Done.')
          }
          else
          {
            console.log('Failed.')
          }
      }).fail(function(xhr, txt, err){
        Notify.toast("Failed to update data", "Failed to update data within editor", "error")
      })

    }
  }
})
