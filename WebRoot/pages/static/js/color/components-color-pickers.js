var ComponentsColorPickers = function() {
    var t = function() {
            jQuery().colorpicker && ($(".colorpicker-default").colorpicker({
                format: "hex"
            }), $(".colorpicker-rgba").colorpicker())
        },
        o = function() {
            $(".color-picker").each(function() {
                $(this).minicolors({
                    control: $(this).attr("data-control") || "hue",
                    defaultValue: $(this).attr("data-defaultValue") || "",
                    inline: "true" === $(this).attr("data-inline"),
                    letterCase: $(this).attr("data-letterCase") || "lowercase",
                    opacity: $(this).attr("data-opacity"),
                    position: $(this).attr("data-position") || "bottom left",
                    change: function(t, o) {
                        // t && (o && (t += ", " + o), "object" == typeof console && console.log(t))
                    },
                    theme: "bootstrap"
                })
            })
        };
    return {
        init: function() {
            o(), t()
        }
    }
}();
jQuery(document).ready(function() {
    ComponentsColorPickers.init()
});