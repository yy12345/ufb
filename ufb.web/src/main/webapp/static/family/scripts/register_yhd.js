/* SVN.committedRevision=1334772 */
(function(a, b) {
    function G(a) {
        var b = F[a] = {};
        return p.each(a.split(s), function(a, c) {
            b[c] = !0
        }), b
    }

    function J(a, c, d) {
        if (d === b && a.nodeType === 1) {
            var e = "data-" + c.replace(I, "-$1").toLowerCase();
            d = a.getAttribute(e);
            if (typeof d == "string") {
                try {
                    d = d === "true" ? !0 : d === "false" ? !1 : d === "null" ? null : +d + "" === d ? +d : H.test(d) ? p.parseJSON(d) : d
                } catch (f) {}
                p.data(a, c, d)
            } else {
                d = b
            }
        }
        return d
    }

    function K(a) {
        var b;
        for (b in a) {
            if (b === "data" && p.isEmptyObject(a[b])) {
                continue
            }
            if (b !== "toJSON") {
                return !1
            }
        }
        return !0
    }

    function ba() {
        return !1
    }

    function bb() {
        return !0
    }

    function bh(a) {
        return !a || !a.parentNode || a.parentNode.nodeType === 11
    }

    function bi(a, b) {
        do {
            a = a[b]
        } while (a && a.nodeType !== 1);
        return a
    }

    function bj(a, b, c) {
        b = b || 0;
        if (p.isFunction(b)) {
            return p.grep(a, function(a, d) {
                var e = !! b.call(a, d, a);
                return e === c
            })
        }
        if (b.nodeType) {
            return p.grep(a, function(a, d) {
                return a === b === c
            })
        }
        if (typeof b == "string") {
            var d = p.grep(a, function(a) {
                return a.nodeType === 1
            });
            if (be.test(b)) {
                return p.filter(b, d, !c)
            }
            b = p.filter(b, d)
        }
        return p.grep(a, function(a, d) {
            return p.inArray(a, b) >= 0 === c
        })
    }

    function bk(a) {
        var b = bl.split("|"),
            c = a.createDocumentFragment();
        if (c.createElement) {
            while (b.length) {
                c.createElement(b.pop())
            }
        }
        return c
    }

    function bC(a, b) {
        return a.getElementsByTagName(b)[0] || a.appendChild(a.ownerDocument.createElement(b))
    }

    function bD(a, b) {
        if (b.nodeType !== 1 || !p.hasData(a)) {
            return
        }
        var c, d, e, f = p._data(a),
            g = p._data(b, f),
            h = f.events;
        if (h) {
            delete g.handle, g.events = {};
            for (c in h) {
                for (d = 0, e = h[c].length; d < e; d++) {
                    p.event.add(b, c, h[c][d])
                }
            }
        }
        g.data && (g.data = p.extend({}, g.data))
    }

    function bE(a, b) {
        var c;
        if (b.nodeType !== 1) {
            return
        }
        b.clearAttributes && b.clearAttributes(), b.mergeAttributes && b.mergeAttributes(a), c = b.nodeName.toLowerCase(), c === "object" ? (b.parentNode && (b.outerHTML = a.outerHTML), p.support.html5Clone && a.innerHTML && !p.trim(b.innerHTML) && (b.innerHTML = a.innerHTML)) : c === "input" && bv.test(a.type) ? (b.defaultChecked = b.checked = a.checked, b.value !== a.value && (b.value = a.value)) : c === "option" ? b.selected = a.defaultSelected : c === "input" || c === "textarea" ? b.defaultValue = a.defaultValue : c === "script" && b.text !== a.text && (b.text = a.text), b.removeAttribute(p.expando)
    }

    function bF(a) {
        return typeof a.getElementsByTagName != "undefined" ? a.getElementsByTagName("*") : typeof a.querySelectorAll != "undefined" ? a.querySelectorAll("*") : []
    }

    function bG(a) {
        bv.test(a.type) && (a.defaultChecked = a.checked)
    }

    function bY(a, b) {
        if (b in a) {
            return b
        }
        var c = b.charAt(0).toUpperCase() + b.slice(1),
            d = b,
            e = bW.length;
        while (e--) {
            b = bW[e] + c;
            if (b in a) {
                return b
            }
        }
        return d
    }

    function bZ(a, b) {
        return a = b || a, p.css(a, "display") === "none" || !p.contains(a.ownerDocument, a)
    }

    function b$(a, b) {
        var c, d, e = [],
            f = 0,
            g = a.length;
        for (; f < g; f++) {
            c = a[f];
            if (!c.style) {
                continue
            }
            e[f] = p._data(c, "olddisplay"), b ? (!e[f] && c.style.display === "none" && (c.style.display = ""), c.style.display === "" && bZ(c) && (e[f] = p._data(c, "olddisplay", cc(c.nodeName)))) : (d = bH(c, "display"), !e[f] && d !== "none" && p._data(c, "olddisplay", d))
        }
        for (f = 0; f < g; f++) {
            c = a[f];
            if (!c.style) {
                continue
            }
            if (!b || c.style.display === "none" || c.style.display === "") {
                c.style.display = b ? e[f] || "" : "none"
            }
        }
        return a
    }

    function b_(a, b, c) {
        var d = bP.exec(b);
        return d ? Math.max(0, d[1] - (c || 0)) + (d[2] || "px") : b
    }

    function ca(a, b, c, d) {
        var e = c === (d ? "border" : "content") ? 4 : b === "width" ? 1 : 0,
            f = 0;
        for (; e < 4; e += 2) {
            c === "margin" && (f += p.css(a, c + bV[e], !0)), d ? (c === "content" && (f -= parseFloat(bH(a, "padding" + bV[e])) || 0), c !== "margin" && (f -= parseFloat(bH(a, "border" + bV[e] + "Width")) || 0)) : (f += parseFloat(bH(a, "padding" + bV[e])) || 0, c !== "padding" && (f += parseFloat(bH(a, "border" + bV[e] + "Width")) || 0))
        }
        return f
    }

    function cb(a, b, c) {
        var d = b === "width" ? a.offsetWidth : a.offsetHeight,
            e = !0,
            f = p.support.boxSizing && p.css(a, "boxSizing") === "border-box";
        if (d <= 0 || d == null) {
            d = bH(a, b);
            if (d < 0 || d == null) {
                d = a.style[b]
            }
            if (bQ.test(d)) {
                return d
            }
            e = f && (p.support.boxSizingReliable || d === a.style[b]), d = parseFloat(d) || 0
        }
        return d + ca(a, b, c || (f ? "border" : "content"), e) + "px"
    }

    function cc(a) {
        if (bS[a]) {
            return bS[a]
        }
        var b = p("<" + a + ">").appendTo(e.body),
            c = b.css("display");
        b.remove();
        if (c === "none" || c === "") {
            bI = e.body.appendChild(bI || p.extend(e.createElement("iframe"), {
                frameBorder: 0,
                width: 0,
                height: 0
            }));
            if (!bJ || !bI.createElement) {
                bJ = (bI.contentWindow || bI.contentDocument).document, bJ.write("<!doctype html><html><body>"), bJ.close()
            }
            b = bJ.body.appendChild(bJ.createElement(a)), c = bH(b, "display"), e.body.removeChild(bI)
        }
        return bS[a] = c, c
    }

    function ci(a, b, c, d) {
        var e;
        if (p.isArray(b)) {
            p.each(b, function(b, e) {
                c || ce.test(a) ? d(a, e) : ci(a + "[" + (typeof e == "object" ? b : "") + "]", e, c, d)
            })
        } else {
            if (!c && p.type(b) === "object") {
                for (e in b) {
                    ci(a + "[" + e + "]", b[e], c, d)
                }
            } else {
                d(a, b)
            }
        }
    }

    function cz(a) {
        return function(b, c) {
            typeof b != "string" && (c = b, b = "*");
            var d, e, f, g = b.toLowerCase().split(s),
                h = 0,
                i = g.length;
            if (p.isFunction(c)) {
                for (; h < i; h++) {
                    d = g[h], f = /^\+/.test(d), f && (d = d.substr(1) || "*"), e = a[d] = a[d] || [], e[f ? "unshift" : "push"](c)
                }
            }
        }
    }

    function cA(a, c, d, e, f, g) {
        f = f || c.dataTypes[0], g = g || {}, g[f] = !0;
        var h, i = a[f],
            j = 0,
            k = i ? i.length : 0,
            l = a === cv;
        for (; j < k && (l || !h); j++) {
            h = i[j](c, d, e), typeof h == "string" && (!l || g[h] ? h = b : (c.dataTypes.unshift(h), h = cA(a, c, d, e, h, g)))
        }
        return (l || !h) && !g["*"] && (h = cA(a, c, d, e, "*", g)), h
    }

    function cB(a, c) {
        var d, e, f = p.ajaxSettings.flatOptions || {};
        for (d in c) {
            c[d] !== b && ((f[d] ? a : e || (e = {}))[d] = c[d])
        }
        e && p.extend(!0, a, e)
    }

    function cC(a, c, d) {
        var e, f, g, h, i = a.contents,
            j = a.dataTypes,
            k = a.responseFields;
        for (f in k) {
            f in d && (c[k[f]] = d[f])
        }
        while (j[0] === "*") {
            j.shift(), e === b && (e = a.mimeType || c.getResponseHeader("content-type"))
        }
        if (e) {
            for (f in i) {
                if (i[f] && i[f].test(e)) {
                    j.unshift(f);
                    break
                }
            }
        }
        if (j[0] in d) {
            g = j[0]
        } else {
            for (f in d) {
                if (!j[0] || a.converters[f + " " + j[0]]) {
                    g = f;
                    break
                }
                h || (h = f)
            }
            g = g || h
        } if (g) {
            return g !== j[0] && j.unshift(g), d[g]
        }
    }

    function cD(a, b) {
        var c, d, e, f, g = a.dataTypes.slice(),
            h = g[0],
            i = {}, j = 0;
        a.dataFilter && (b = a.dataFilter(b, a.dataType));
        if (g[1]) {
            for (c in a.converters) {
                i[c.toLowerCase()] = a.converters[c]
            }
        }
        for (; e = g[++j];) {
            if (e !== "*") {
                if (h !== "*" && h !== e) {
                    c = i[h + " " + e] || i["* " + e];
                    if (!c) {
                        for (d in i) {
                            f = d.split(" ");
                            if (f[1] === e) {
                                c = i[h + " " + f[0]] || i["* " + f[0]];
                                if (c) {
                                    c === !0 ? c = i[d] : i[d] !== !0 && (e = f[0], g.splice(j--, 0, e));
                                    break
                                }
                            }
                        }
                    }
                    if (c !== !0) {
                        if (c && a["throws"]) {
                            b = c(b)
                        } else {
                            try {
                                b = c(b)
                            } catch (k) {
                                return {
                                    state: "parsererror",
                                    error: c ? k : "No conversion from " + h + " to " + e
                                }
                            }
                        }
                    }
                }
                h = e
            }
        }
        return {
            state: "success",
            data: b
        }
    }

    function cL() {
        try {
            return new a.XMLHttpRequest
        } catch (b) {}
    }

    function cM() {
        try {
            return new a.ActiveXObject("Microsoft.XMLHTTP")
        } catch (b) {}
    }

    function cU() {
        return setTimeout(function() {
            cN = b
        }, 0), cN = p.now()
    }

    function cV(a, b) {
        p.each(b, function(b, c) {
            var d = (cT[b] || []).concat(cT["*"]),
                e = 0,
                f = d.length;
            for (; e < f; e++) {
                if (d[e].call(a, b, c)) {
                    return
                }
            }
        })
    }

    function cW(a, b, c) {
        var d, e = 0,
            f = 0,
            g = cS.length,
            h = p.Deferred().always(function() {
                delete i.elem
            }),
            i = function() {
                var b = cN || cU(),
                    c = Math.max(0, j.startTime + j.duration - b),
                    d = 1 - (c / j.duration || 0),
                    e = 0,
                    f = j.tweens.length;
                for (; e < f; e++) {
                    j.tweens[e].run(d)
                }
                return h.notifyWith(a, [j, d, c]), d < 1 && f ? c : (h.resolveWith(a, [j]), !1)
            }, j = h.promise({
                elem: a,
                props: p.extend({}, b),
                opts: p.extend(!0, {
                    specialEasing: {}
                }, c),
                originalProperties: b,
                originalOptions: c,
                startTime: cN || cU(),
                duration: c.duration,
                tweens: [],
                createTween: function(b, c, d) {
                    var e = p.Tween(a, j.opts, b, c, j.opts.specialEasing[b] || j.opts.easing);
                    return j.tweens.push(e), e
                },
                stop: function(b) {
                    var c = 0,
                        d = b ? j.tweens.length : 0;
                    for (; c < d; c++) {
                        j.tweens[c].run(1)
                    }
                    return b ? h.resolveWith(a, [j, b]) : h.rejectWith(a, [j, b]), this
                }
            }),
            k = j.props;
        cX(k, j.opts.specialEasing);
        for (; e < g; e++) {
            d = cS[e].call(j, a, k, j.opts);
            if (d) {
                return d
            }
        }
        return cV(j, k), p.isFunction(j.opts.start) && j.opts.start.call(a, j), p.fx.timer(p.extend(i, {
            anim: j,
            queue: j.opts.queue,
            elem: a
        })), j.progress(j.opts.progress).done(j.opts.done, j.opts.complete).fail(j.opts.fail).always(j.opts.always)
    }

    function cX(a, b) {
        var c, d, e, f, g;
        for (c in a) {
            d = p.camelCase(c), e = b[d], f = a[c], p.isArray(f) && (e = f[1], f = a[c] = f[0]), c !== d && (a[d] = f, delete a[c]), g = p.cssHooks[d];
            if (g && "expand" in g) {
                f = g.expand(f), delete a[d];
                for (c in f) {
                    c in a || (a[c] = f[c], b[c] = e)
                }
            } else {
                b[d] = e
            }
        }
    }

    function cY(a, b, c) {
        var d, e, f, g, h, i, j, k, l = this,
            m = a.style,
            n = {}, o = [],
            q = a.nodeType && bZ(a);
        c.queue || (j = p._queueHooks(a, "fx"), j.unqueued == null && (j.unqueued = 0, k = j.empty.fire, j.empty.fire = function() {
            j.unqueued || k()
        }), j.unqueued++, l.always(function() {
            l.always(function() {
                j.unqueued--, p.queue(a, "fx").length || j.empty.fire()
            })
        })), a.nodeType === 1 && ("height" in b || "width" in b) && (c.overflow = [m.overflow, m.overflowX, m.overflowY], p.css(a, "display") === "inline" && p.css(a, "float") === "none" && (!p.support.inlineBlockNeedsLayout || cc(a.nodeName) === "inline" ? m.display = "inline-block" : m.zoom = 1)), c.overflow && (m.overflow = "hidden", p.support.shrinkWrapBlocks || l.done(function() {
            m.overflow = c.overflow[0], m.overflowX = c.overflow[1], m.overflowY = c.overflow[2]
        }));
        for (d in b) {
            f = b[d];
            if (cP.exec(f)) {
                delete b[d];
                if (f === (q ? "hide" : "show")) {
                    continue
                }
                o.push(d)
            }
        }
        g = o.length;
        if (g) {
            h = p._data(a, "fxshow") || p._data(a, "fxshow", {}), q ? p(a).show() : l.done(function() {
                p(a).hide()
            }), l.done(function() {
                var b;
                p.removeData(a, "fxshow", !0);
                for (b in n) {
                    p.style(a, b, n[b])
                }
            });
            for (d = 0; d < g; d++) {
                e = o[d], i = l.createTween(e, q ? h[e] : 0), n[e] = h[e] || p.style(a, e), e in h || (h[e] = i.start, q && (i.end = i.start, i.start = e === "width" || e === "height" ? 1 : 0))
            }
        }
    }

    function cZ(a, b, c, d, e) {
        return new cZ.prototype.init(a, b, c, d, e)
    }

    function c$(a, b) {
        var c, d = {
                height: a
            }, e = 0;
        b = b ? 1 : 0;
        for (; e < 4; e += 2 - b) {
            c = bV[e], d["margin" + c] = d["padding" + c] = a
        }
        return b && (d.opacity = d.width = a), d
    }

    function da(a) {
        return p.isWindow(a) ? a : a.nodeType === 9 ? a.defaultView || a.parentWindow : !1
    }
    var c, d, e = a.document,
        f = a.location,
        g = a.navigator,
        h = a.jQuery,
        i = a.$,
        j = Array.prototype.push,
        k = Array.prototype.slice,
        l = Array.prototype.indexOf,
        m = Object.prototype.toString,
        n = Object.prototype.hasOwnProperty,
        o = String.prototype.trim,
        p = function(a, b) {
            return new p.fn.init(a, b, c)
        }, q = /[\-+]?(?:\d*\.|)\d+(?:[eE][\-+]?\d+|)/.source,
        r = /\S/,
        s = /\s+/,
        t = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g,
        u = /^(?:[^#<]*(<[\w\W]+>)[^>]*$|#([\w\-]*)$)/,
        v = /^<(\w+)\s*\/?>(?:<\/\1>|)$/,
        w = /^[\],:{}\s]*$/,
        x = /(?:^|:|,)(?:\s*\[)+/g,
        y = /\\(?:["\\\/bfnrt]|u[\da-fA-F]{4})/g,
        z = /"[^"\\\r\n]*"|true|false|null|-?(?:\d\d*\.|)\d+(?:[eE][\-+]?\d+|)/g,
        A = /^-ms-/,
        B = /-([\da-z])/gi,
        C = function(a, b) {
            return (b + "").toUpperCase()
        }, D = function() {
            e.addEventListener ? (e.removeEventListener("DOMContentLoaded", D, !1), p.ready()) : e.readyState === "complete" && (e.detachEvent("onreadystatechange", D), p.ready())
        }, E = {};
    p.fn = p.prototype = {
        constructor: p,
        init: function(a, c, d) {
            var f, g, h, i;
            if (!a) {
                return this
            }
            if (a.nodeType) {
                return this.context = this[0] = a, this.length = 1, this
            }
            if (typeof a == "string") {
                a.charAt(0) === "<" && a.charAt(a.length - 1) === ">" && a.length >= 3 ? f = [null, a, null] : f = u.exec(a);
                if (f && (f[1] || !c)) {
                    if (f[1]) {
                        return c = c instanceof p ? c[0] : c, i = c && c.nodeType ? c.ownerDocument || c : e, a = p.parseHTML(f[1], i, !0), v.test(f[1]) && p.isPlainObject(c) && this.attr.call(a, c, !0), p.merge(this, a)
                    }
                    g = e.getElementById(f[2]);
                    if (g && g.parentNode) {
                        if (g.id !== f[2]) {
                            return d.find(a)
                        }
                        this.length = 1, this[0] = g
                    }
                    return this.context = e, this.selector = a, this
                }
                return !c || c.jquery ? (c || d).find(a) : this.constructor(c).find(a)
            }
            return p.isFunction(a) ? d.ready(a) : (a.selector !== b && (this.selector = a.selector, this.context = a.context), p.makeArray(a, this))
        },
        selector: "",
        jquery: "1.8.1",
        length: 0,
        size: function() {
            return this.length
        },
        toArray: function() {
            return k.call(this)
        },
        get: function(a) {
            return a == null ? this.toArray() : a < 0 ? this[this.length + a] : this[a]
        },
        pushStack: function(a, b, c) {
            var d = p.merge(this.constructor(), a);
            return d.prevObject = this, d.context = this.context, b === "find" ? d.selector = this.selector + (this.selector ? " " : "") + c : b && (d.selector = this.selector + "." + b + "(" + c + ")"), d
        },
        each: function(a, b) {
            return p.each(this, a, b)
        },
        ready: function(a) {
            return p.ready.promise().done(a), this
        },
        eq: function(a) {
            return a = +a, a === -1 ? this.slice(a) : this.slice(a, a + 1)
        },
        first: function() {
            return this.eq(0)
        },
        last: function() {
            return this.eq(-1)
        },
        slice: function() {
            return this.pushStack(k.apply(this, arguments), "slice", k.call(arguments).join(","))
        },
        map: function(a) {
            return this.pushStack(p.map(this, function(b, c) {
                return a.call(b, c, b)
            }))
        },
        end: function() {
            return this.prevObject || this.constructor(null)
        },
        push: j,
        sort: [].sort,
        splice: [].splice
    }, p.fn.init.prototype = p.fn, p.extend = p.fn.extend = function() {
        var a, c, d, e, f, g, h = arguments[0] || {}, i = 1,
            j = arguments.length,
            k = !1;
        typeof h == "boolean" && (k = h, h = arguments[1] || {}, i = 2), typeof h != "object" && !p.isFunction(h) && (h = {}), j === i && (h = this, --i);
        for (; i < j; i++) {
            if ((a = arguments[i]) != null) {
                for (c in a) {
                    d = h[c], e = a[c];
                    if (h === e) {
                        continue
                    }
                    k && e && (p.isPlainObject(e) || (f = p.isArray(e))) ? (f ? (f = !1, g = d && p.isArray(d) ? d : []) : g = d && p.isPlainObject(d) ? d : {}, h[c] = p.extend(k, g, e)) : e !== b && (h[c] = e)
                }
            }
        }
        return h
    }, p.extend({
        noConflict: function(b) {
            return a.$ === p && (a.$ = i), b && a.jQuery === p && (a.jQuery = h), p
        },
        isReady: !1,
        readyWait: 1,
        holdReady: function(a) {
            a ? p.readyWait++ : p.ready(!0)
        },
        ready: function(a) {
            if (a === !0 ? --p.readyWait : p.isReady) {
                return
            }
            if (!e.body) {
                return setTimeout(p.ready, 1)
            }
            p.isReady = !0;
            if (a !== !0 && --p.readyWait > 0) {
                return
            }
            d.resolveWith(e, [p]), p.fn.trigger && p(e).trigger("ready").off("ready")
        },
        isFunction: function(a) {
            return p.type(a) === "function"
        },
        isArray: Array.isArray || function(a) {
            return p.type(a) === "array"
        },
        isWindow: function(a) {
            return a != null && a == a.window
        },
        isNumeric: function(a) {
            return !isNaN(parseFloat(a)) && isFinite(a)
        },
        type: function(a) {
            return a == null ? String(a) : E[m.call(a)] || "object"
        },
        isPlainObject: function(a) {
            if (!a || p.type(a) !== "object" || a.nodeType || p.isWindow(a)) {
                return !1
            }
            try {
                if (a.constructor && !n.call(a, "constructor") && !n.call(a.constructor.prototype, "isPrototypeOf")) {
                    return !1
                }
            } catch (c) {
                return !1
            }
            var d;
            for (d in a) {}
            return d === b || n.call(a, d)
        },
        isEmptyObject: function(a) {
            var b;
            for (b in a) {
                return !1
            }
            return !0
        },
        error: function(a) {
            throw new Error(a)
        },
        parseHTML: function(a, b, c) {
            var d;
            return !a || typeof a != "string" ? null : (typeof b == "boolean" && (c = b, b = 0), b = b || e, (d = v.exec(a)) ? [b.createElement(d[1])] : (d = p.buildFragment([a], b, c ? null : []), p.merge([], (d.cacheable ? p.clone(d.fragment) : d.fragment).childNodes)))
        },
        parseJSON: function(b) {
            if (!b || typeof b != "string") {
                return null
            }
            b = p.trim(b);
            if (a.JSON && a.JSON.parse) {
                return a.JSON.parse(b)
            }
            if (w.test(b.replace(y, "@").replace(z, "]").replace(x, ""))) {
                return (new Function("return " + b))()
            }
            p.error("Invalid JSON: " + b)
        },
        parseXML: function(c) {
            var d, e;
            if (!c || typeof c != "string") {
                return null
            }
            try {
                a.DOMParser ? (e = new DOMParser, d = e.parseFromString(c, "text/xml")) : (d = new ActiveXObject("Microsoft.XMLDOM"), d.async = "false", d.loadXML(c))
            } catch (f) {
                d = b
            }
            return (!d || !d.documentElement || d.getElementsByTagName("parsererror").length) && p.error("Invalid XML: " + c), d
        },
        noop: function() {},
        globalEval: function(b) {
            b && r.test(b) && (a.execScript || function(b) {
                a.eval.call(a, b)
            })(b)
        },
        camelCase: function(a) {
            return a.replace(A, "ms-").replace(B, C)
        },
        nodeName: function(a, b) {
            return a.nodeName && a.nodeName.toUpperCase() === b.toUpperCase()
        },
        each: function(a, c, d) {
            var e, f = 0,
                g = a.length,
                h = g === b || p.isFunction(a);
            if (d) {
                if (h) {
                    for (e in a) {
                        if (c.apply(a[e], d) === !1) {
                            break
                        }
                    }
                } else {
                    for (; f < g;) {
                        if (c.apply(a[f++], d) === !1) {
                            break
                        }
                    }
                }
            } else {
                if (h) {
                    for (e in a) {
                        if (c.call(a[e], e, a[e]) === !1) {
                            break
                        }
                    }
                } else {
                    for (; f < g;) {
                        if (c.call(a[f], f, a[f++]) === !1) {
                            break
                        }
                    }
                }
            }
            return a
        },
        trim: o && !o.call(" ") ? function(a) {
            return a == null ? "" : o.call(a)
        } : function(a) {
            return a == null ? "" : a.toString().replace(t, "")
        },
        makeArray: function(a, b) {
            var c, d = b || [];
            return a != null && (c = p.type(a), a.length == null || c === "string" || c === "function" || c === "regexp" || p.isWindow(a) ? j.call(d, a) : p.merge(d, a)), d
        },
        inArray: function(a, b, c) {
            var d;
            if (b) {
                if (l) {
                    return l.call(b, a, c)
                }
                d = b.length, c = c ? c < 0 ? Math.max(0, d + c) : c : 0;
                for (; c < d; c++) {
                    if (c in b && b[c] === a) {
                        return c
                    }
                }
            }
            return -1
        },
        merge: function(a, c) {
            var d = c.length,
                e = a.length,
                f = 0;
            if (typeof d == "number") {
                for (; f < d; f++) {
                    a[e++] = c[f]
                }
            } else {
                while (c[f] !== b) {
                    a[e++] = c[f++]
                }
            }
            return a.length = e, a
        },
        grep: function(a, b, c) {
            var d, e = [],
                f = 0,
                g = a.length;
            c = !! c;
            for (; f < g; f++) {
                d = !! b(a[f], f), c !== d && e.push(a[f])
            }
            return e
        },
        map: function(a, c, d) {
            var e, f, g = [],
                h = 0,
                i = a.length,
                j = a instanceof p || i !== b && typeof i == "number" && (i > 0 && a[0] && a[i - 1] || i === 0 || p.isArray(a));
            if (j) {
                for (; h < i; h++) {
                    e = c(a[h], h, d), e != null && (g[g.length] = e)
                }
            } else {
                for (f in a) {
                    e = c(a[f], f, d), e != null && (g[g.length] = e)
                }
            }
            return g.concat.apply([], g)
        },
        guid: 1,
        proxy: function(a, c) {
            var d, e, f;
            return typeof c == "string" && (d = a[c], c = a, a = d), p.isFunction(a) ? (e = k.call(arguments, 2), f = function() {
                return a.apply(c, e.concat(k.call(arguments)))
            }, f.guid = a.guid = a.guid || f.guid || p.guid++, f) : b
        },
        access: function(a, c, d, e, f, g, h) {
            var i, j = d == null,
                k = 0,
                l = a.length;
            if (d && typeof d == "object") {
                for (k in d) {
                    p.access(a, c, k, d[k], 1, g, e)
                }
                f = 1
            } else {
                if (e !== b) {
                    i = h === b && p.isFunction(e), j && (i ? (i = c, c = function(a, b, c) {
                        return i.call(p(a), c)
                    }) : (c.call(a, e), c = null));
                    if (c) {
                        for (; k < l; k++) {
                            c(a[k], d, i ? e.call(a[k], k, c(a[k], d)) : e, h)
                        }
                    }
                    f = 1
                }
            }
            return f ? a : j ? c.call(a) : l ? c(a[0], d) : g
        },
        now: function() {
            return (new Date).getTime()
        }
    }), p.ready.promise = function(b) {
        if (!d) {
            d = p.Deferred();
            if (e.readyState === "complete") {
                setTimeout(p.ready, 1)
            } else {
                if (e.addEventListener) {
                    e.addEventListener("DOMContentLoaded", D, !1), a.addEventListener("load", p.ready, !1)
                } else {
                    e.attachEvent("onreadystatechange", D), a.attachEvent("onload", p.ready);
                    var c = !1;
                    try {
                        c = a.frameElement == null && e.documentElement
                    } catch (f) {}
                    c && c.doScroll && function g() {
                        if (!p.isReady) {
                            try {
                                c.doScroll("left")
                            } catch (a) {
                                return setTimeout(g, 50)
                            }
                            p.ready()
                        }
                    }()
                }
            }
        }
        return d.promise(b)
    }, p.each("Boolean Number String Function Array Date RegExp Object".split(" "), function(a, b) {
        E["[object " + b + "]"] = b.toLowerCase()
    }), c = p(e);
    var F = {};
    p.Callbacks = function(a) {
        a = typeof a == "string" ? F[a] || G(a) : p.extend({}, a);
        var c, d, e, f, g, h, i = [],
            j = !a.once && [],
            k = function(b) {
                c = a.memory && b, d = !0, h = f || 0, f = 0, g = i.length, e = !0;
                for (; i && h < g; h++) {
                    if (i[h].apply(b[0], b[1]) === !1 && a.stopOnFalse) {
                        c = !1;
                        break
                    }
                }
                e = !1, i && (j ? j.length && k(j.shift()) : c ? i = [] : l.disable())
            }, l = {
                add: function() {
                    if (i) {
                        var b = i.length;
                        (function d(b) {
                            p.each(b, function(b, c) {
                                var e = p.type(c);
                                e === "function" && (!a.unique || !l.has(c)) ? i.push(c) : c && c.length && e !== "string" && d(c)
                            })
                        })(arguments), e ? g = i.length : c && (f = b, k(c))
                    }
                    return this
                },
                remove: function() {
                    return i && p.each(arguments, function(a, b) {
                        var c;
                        while ((c = p.inArray(b, i, c)) > -1) {
                            i.splice(c, 1), e && (c <= g && g--, c <= h && h--)
                        }
                    }), this
                },
                has: function(a) {
                    return p.inArray(a, i) > -1
                },
                empty: function() {
                    return i = [], this
                },
                disable: function() {
                    return i = j = c = b, this
                },
                disabled: function() {
                    return !i
                },
                lock: function() {
                    return j = b, c || l.disable(), this
                },
                locked: function() {
                    return !j
                },
                fireWith: function(a, b) {
                    return b = b || [], b = [a, b.slice ? b.slice() : b], i && (!d || j) && (e ? j.push(b) : k(b)), this
                },
                fire: function() {
                    return l.fireWith(this, arguments), this
                },
                fired: function() {
                    return !!d
                }
            };
        return l
    }, p.extend({
        Deferred: function(a) {
            var b = [
                ["resolve", "done", p.Callbacks("once memory"), "resolved"],
                ["reject", "fail", p.Callbacks("once memory"), "rejected"],
                ["notify", "progress", p.Callbacks("memory")]
            ],
                c = "pending",
                d = {
                    state: function() {
                        return c
                    },
                    always: function() {
                        return e.done(arguments).fail(arguments), this
                    },
                    then: function() {
                        var a = arguments;
                        return p.Deferred(function(c) {
                            p.each(b, function(b, d) {
                                var f = d[0],
                                    g = a[b];
                                e[d[1]](p.isFunction(g) ? function() {
                                    var a = g.apply(this, arguments);
                                    a && p.isFunction(a.promise) ? a.promise().done(c.resolve).fail(c.reject).progress(c.notify) : c[f + "With"](this === e ? c : this, [a])
                                } : c[f])
                            }), a = null
                        }).promise()
                    },
                    promise: function(a) {
                        return typeof a == "object" ? p.extend(a, d) : d
                    }
                }, e = {};
            return d.pipe = d.then, p.each(b, function(a, f) {
                var g = f[2],
                    h = f[3];
                d[f[1]] = g.add, h && g.add(function() {
                    c = h
                }, b[a ^ 1][2].disable, b[2][2].lock), e[f[0]] = g.fire, e[f[0] + "With"] = g.fireWith
            }), d.promise(e), a && a.call(e, e), e
        },
        when: function(a) {
            var b = 0,
                c = k.call(arguments),
                d = c.length,
                e = d !== 1 || a && p.isFunction(a.promise) ? d : 0,
                f = e === 1 ? a : p.Deferred(),
                g = function(a, b, c) {
                    return function(d) {
                        b[a] = this, c[a] = arguments.length > 1 ? k.call(arguments) : d, c === h ? f.notifyWith(b, c) : --e || f.resolveWith(b, c)
                    }
                }, h, i, j;
            if (d > 1) {
                h = new Array(d), i = new Array(d), j = new Array(d);
                for (; b < d; b++) {
                    c[b] && p.isFunction(c[b].promise) ? c[b].promise().done(g(b, j, c)).fail(f.reject).progress(g(b, i, h)) : --e
                }
            }
            return e || f.resolveWith(j, c), f.promise()
        }
    }), p.support = function() {
        var b, c, d, f, g, h, i, j, k, l, m, n = e.createElement("div");
        n.setAttribute("className", "t"), n.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", c = n.getElementsByTagName("*"), d = n.getElementsByTagName("a")[0], d.style.cssText = "top:1px;float:left;opacity:.5";
        if (!c || !c.length || !d) {
            return {}
        }
        f = e.createElement("select"), g = f.appendChild(e.createElement("option")), h = n.getElementsByTagName("input")[0], b = {
            leadingWhitespace: n.firstChild.nodeType === 3,
            tbody: !n.getElementsByTagName("tbody").length,
            htmlSerialize: !! n.getElementsByTagName("link").length,
            style: /top/.test(d.getAttribute("style")),
            hrefNormalized: d.getAttribute("href") === "/a",
            opacity: /^0.5/.test(d.style.opacity),
            cssFloat: !! d.style.cssFloat,
            checkOn: h.value === "on",
            optSelected: g.selected,
            getSetAttribute: n.className !== "t",
            enctype: !! e.createElement("form").enctype,
            html5Clone: e.createElement("nav").cloneNode(!0).outerHTML !== "<:nav></:nav>",
            boxModel: e.compatMode === "CSS1Compat",
            submitBubbles: !0,
            changeBubbles: !0,
            focusinBubbles: !1,
            deleteExpando: !0,
            noCloneEvent: !0,
            inlineBlockNeedsLayout: !1,
            shrinkWrapBlocks: !1,
            reliableMarginRight: !0,
            boxSizingReliable: !0,
            pixelPosition: !1
        }, h.checked = !0, b.noCloneChecked = h.cloneNode(!0).checked, f.disabled = !0, b.optDisabled = !g.disabled;
        try {
            delete n.test
        } catch (o) {
            b.deleteExpando = !1
        }!n.addEventListener && n.attachEvent && n.fireEvent && (n.attachEvent("onclick", m = function() {
            b.noCloneEvent = !1
        }), n.cloneNode(!0).fireEvent("onclick"), n.detachEvent("onclick", m)), h = e.createElement("input"), h.value = "t", h.setAttribute("type", "radio"), b.radioValue = h.value === "t", h.setAttribute("checked", "checked"), h.setAttribute("name", "t"), n.appendChild(h), i = e.createDocumentFragment(), i.appendChild(n.lastChild), b.checkClone = i.cloneNode(!0).cloneNode(!0).lastChild.checked, b.appendChecked = h.checked, i.removeChild(h), i.appendChild(n);
        if (n.attachEvent) {
            for (k in {
                submit: !0,
                change: !0,
                focusin: !0
            }) {
                j = "on" + k, l = j in n, l || (n.setAttribute(j, "return;"), l = typeof n[j] == "function"), b[k + "Bubbles"] = l
            }
        }
        return p(function() {
            var c, d, f, g, h = "padding:0;margin:0;border:0;display:block;overflow:hidden;",
                i = e.getElementsByTagName("body")[0];
            if (!i) {
                return
            }
            c = e.createElement("div"), c.style.cssText = "visibility:hidden;border:0;width:0;height:0;position:static;top:0;margin-top:1px", i.insertBefore(c, i.firstChild), d = e.createElement("div"), c.appendChild(d), d.innerHTML = "<table><tr><td></td><td>t</td></tr></table>", f = d.getElementsByTagName("td"), f[0].style.cssText = "padding:0;margin:0;border:0;display:none", l = f[0].offsetHeight === 0, f[0].style.display = "", f[1].style.display = "none", b.reliableHiddenOffsets = l && f[0].offsetHeight === 0, d.innerHTML = "", d.style.cssText = "box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;padding:1px;border:1px;display:block;width:4px;margin-top:1%;position:absolute;top:1%;", b.boxSizing = d.offsetWidth === 4, b.doesNotIncludeMarginInBodyOffset = i.offsetTop !== 1, a.getComputedStyle && (b.pixelPosition = (a.getComputedStyle(d, null) || {}).top !== "1%", b.boxSizingReliable = (a.getComputedStyle(d, null) || {
                width: "4px"
            }).width === "4px", g = e.createElement("div"), g.style.cssText = d.style.cssText = h, g.style.marginRight = g.style.width = "0", d.style.width = "1px", d.appendChild(g), b.reliableMarginRight = !parseFloat((a.getComputedStyle(g, null) || {}).marginRight)), typeof d.style.zoom != "undefined" && (d.innerHTML = "", d.style.cssText = h + "width:1px;padding:1px;display:inline;zoom:1", b.inlineBlockNeedsLayout = d.offsetWidth === 3, d.style.display = "block", d.style.overflow = "visible", d.innerHTML = "<div></div>", d.firstChild.style.width = "5px", b.shrinkWrapBlocks = d.offsetWidth !== 3, c.style.zoom = 1), i.removeChild(c), c = d = f = g = null
        }), i.removeChild(n), c = d = f = g = h = i = n = null, b
    }();
    var H = /(?:\{[\s\S]*\}|\[[\s\S]*\])$/,
        I = /([A-Z])/g;
    p.extend({
        cache: {},
        deletedIds: [],
        uuid: 0,
        expando: "jQuery" + (p.fn.jquery + Math.random()).replace(/\D/g, ""),
        noData: {
            embed: !0,
            object: "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000",
            applet: !0
        },
        hasData: function(a) {
            return a = a.nodeType ? p.cache[a[p.expando]] : a[p.expando], !! a && !K(a)
        },
        data: function(a, c, d, e) {
            if (!p.acceptData(a)) {
                return
            }
            var f, g, h = p.expando,
                i = typeof c == "string",
                j = a.nodeType,
                k = j ? p.cache : a,
                l = j ? a[h] : a[h] && h;
            if ((!l || !k[l] || !e && !k[l].data) && i && d === b) {
                return
            }
            l || (j ? a[h] = l = p.deletedIds.pop() || ++p.uuid : l = h), k[l] || (k[l] = {}, j || (k[l].toJSON = p.noop));
            if (typeof c == "object" || typeof c == "function") {
                e ? k[l] = p.extend(k[l], c) : k[l].data = p.extend(k[l].data, c)
            }
            return f = k[l], e || (f.data || (f.data = {}), f = f.data), d !== b && (f[p.camelCase(c)] = d), i ? (g = f[c], g == null && (g = f[p.camelCase(c)])) : g = f, g
        },
        removeData: function(a, b, c) {
            if (!p.acceptData(a)) {
                return
            }
            var d, e, f, g = a.nodeType,
                h = g ? p.cache : a,
                i = g ? a[p.expando] : p.expando;
            if (!h[i]) {
                return
            }
            if (b) {
                d = c ? h[i] : h[i].data;
                if (d) {
                    p.isArray(b) || (b in d ? b = [b] : (b = p.camelCase(b), b in d ? b = [b] : b = b.split(" ")));
                    for (e = 0, f = b.length; e < f; e++) {
                        delete d[b[e]]
                    }
                    if (!(c ? K : p.isEmptyObject)(d)) {
                        return
                    }
                }
            }
            if (!c) {
                delete h[i].data;
                if (!K(h[i])) {
                    return
                }
            }
            g ? p.cleanData([a], !0) : p.support.deleteExpando || h != h.window ? delete h[i] : h[i] = null
        },
        _data: function(a, b, c) {
            return p.data(a, b, c, !0)
        },
        acceptData: function(a) {
            var b = a.nodeName && p.noData[a.nodeName.toLowerCase()];
            return !b || b !== !0 && a.getAttribute("classid") === b
        }
    }), p.fn.extend({
        data: function(a, c) {
            var d, e, f, g, h, i = this[0],
                j = 0,
                k = null;
            if (a === b) {
                if (this.length) {
                    k = p.data(i);
                    if (i.nodeType === 1 && !p._data(i, "parsedAttrs")) {
                        f = i.attributes;
                        for (h = f.length; j < h; j++) {
                            g = f[j].name, g.indexOf("data-") === 0 && (g = p.camelCase(g.substring(5)), J(i, g, k[g]))
                        }
                        p._data(i, "parsedAttrs", !0)
                    }
                }
                return k
            }
            return typeof a == "object" ? this.each(function() {
                p.data(this, a)
            }) : (d = a.split(".", 2), d[1] = d[1] ? "." + d[1] : "", e = d[1] + "!", p.access(this, function(c) {
                if (c === b) {
                    return k = this.triggerHandler("getData" + e, [d[0]]), k === b && i && (k = p.data(i, a), k = J(i, a, k)), k === b && d[1] ? this.data(d[0]) : k
                }
                d[1] = c, this.each(function() {
                    var b = p(this);
                    b.triggerHandler("setData" + e, d), p.data(this, a, c), b.triggerHandler("changeData" + e, d)
                })
            }, null, c, arguments.length > 1, null, !1))
        },
        removeData: function(a) {
            return this.each(function() {
                p.removeData(this, a)
            })
        }
    }), p.extend({
        queue: function(a, b, c) {
            var d;
            if (a) {
                return b = (b || "fx") + "queue", d = p._data(a, b), c && (!d || p.isArray(c) ? d = p._data(a, b, p.makeArray(c)) : d.push(c)), d || []
            }
        },
        dequeue: function(a, b) {
            b = b || "fx";
            var c = p.queue(a, b),
                d = c.length,
                e = c.shift(),
                f = p._queueHooks(a, b),
                g = function() {
                    p.dequeue(a, b)
                };
            e === "inprogress" && (e = c.shift(), d--), e && (b === "fx" && c.unshift("inprogress"), delete f.stop, e.call(a, g, f)), !d && f && f.empty.fire()
        },
        _queueHooks: function(a, b) {
            var c = b + "queueHooks";
            return p._data(a, c) || p._data(a, c, {
                empty: p.Callbacks("once memory").add(function() {
                    p.removeData(a, b + "queue", !0), p.removeData(a, c, !0)
                })
            })
        }
    }), p.fn.extend({
        queue: function(a, c) {
            var d = 2;
            return typeof a != "string" && (c = a, a = "fx", d--), arguments.length < d ? p.queue(this[0], a) : c === b ? this : this.each(function() {
                var b = p.queue(this, a, c);
                p._queueHooks(this, a), a === "fx" && b[0] !== "inprogress" && p.dequeue(this, a)
            })
        },
        dequeue: function(a) {
            return this.each(function() {
                p.dequeue(this, a)
            })
        },
        delay: function(a, b) {
            return a = p.fx ? p.fx.speeds[a] || a : a, b = b || "fx", this.queue(b, function(b, c) {
                var d = setTimeout(b, a);
                c.stop = function() {
                    clearTimeout(d)
                }
            })
        },
        clearQueue: function(a) {
            return this.queue(a || "fx", [])
        },
        promise: function(a, c) {
            var d, e = 1,
                f = p.Deferred(),
                g = this,
                h = this.length,
                i = function() {
                    --e || f.resolveWith(g, [g])
                };
            typeof a != "string" && (c = a, a = b), a = a || "fx";
            while (h--) {
                d = p._data(g[h], a + "queueHooks"), d && d.empty && (e++, d.empty.add(i))
            }
            return i(), f.promise(c)
        }
    });
    var L, M, N, O = /[\t\r\n]/g,
        P = /\r/g,
        Q = /^(?:button|input)$/i,
        R = /^(?:button|input|object|select|textarea)$/i,
        S = /^a(?:rea|)$/i,
        T = /^(?:autofocus|autoplay|async|checked|controls|defer|disabled|hidden|loop|multiple|open|readonly|required|scoped|selected)$/i,
        U = p.support.getSetAttribute;
    p.fn.extend({
        attr: function(a, b) {
            return p.access(this, p.attr, a, b, arguments.length > 1)
        },
        removeAttr: function(a) {
            return this.each(function() {
                p.removeAttr(this, a)
            })
        },
        prop: function(a, b) {
            return p.access(this, p.prop, a, b, arguments.length > 1)
        },
        removeProp: function(a) {
            return a = p.propFix[a] || a, this.each(function() {
                try {
                    this[a] = b, delete this[a]
                } catch (c) {}
            })
        },
        addClass: function(a) {
            var b, c, d, e, f, g, h;
            if (p.isFunction(a)) {
                return this.each(function(b) {
                    p(this).addClass(a.call(this, b, this.className))
                })
            }
            if (a && typeof a == "string") {
                b = a.split(s);
                for (c = 0, d = this.length; c < d; c++) {
                    e = this[c];
                    if (e.nodeType === 1) {
                        if (!e.className && b.length === 1) {
                            e.className = a
                        } else {
                            f = " " + e.className + " ";
                            for (g = 0, h = b.length; g < h; g++) {~
                                f.indexOf(" " + b[g] + " ") || (f += b[g] + " ")
                            }
                            e.className = p.trim(f)
                        }
                    }
                }
            }
            return this
        },
        removeClass: function(a) {
            var c, d, e, f, g, h, i;
            if (p.isFunction(a)) {
                return this.each(function(b) {
                    p(this).removeClass(a.call(this, b, this.className))
                })
            }
            if (a && typeof a == "string" || a === b) {
                c = (a || "").split(s);
                for (h = 0, i = this.length; h < i; h++) {
                    e = this[h];
                    if (e.nodeType === 1 && e.className) {
                        d = (" " + e.className + " ").replace(O, " ");
                        for (f = 0, g = c.length; f < g; f++) {
                            while (d.indexOf(" " + c[f] + " ") > -1) {
                                d = d.replace(" " + c[f] + " ", " ")
                            }
                        }
                        e.className = a ? p.trim(d) : ""
                    }
                }
            }
            return this
        },
        toggleClass: function(a, b) {
            var c = typeof a,
                d = typeof b == "boolean";
            return p.isFunction(a) ? this.each(function(c) {
                p(this).toggleClass(a.call(this, c, this.className, b), b)
            }) : this.each(function() {
                if (c === "string") {
                    var e, f = 0,
                        g = p(this),
                        h = b,
                        i = a.split(s);
                    while (e = i[f++]) {
                        h = d ? h : !g.hasClass(e), g[h ? "addClass" : "removeClass"](e)
                    }
                } else {
                    if (c === "undefined" || c === "boolean") {
                        this.className && p._data(this, "__className__", this.className), this.className = this.className || a === !1 ? "" : p._data(this, "__className__") || ""
                    }
                }
            })
        },
        hasClass: function(a) {
            var b = " " + a + " ",
                c = 0,
                d = this.length;
            for (; c < d; c++) {
                if (this[c].nodeType === 1 && (" " + this[c].className + " ").replace(O, " ").indexOf(b) > -1) {
                    return !0
                }
            }
            return !1
        },
        val: function(a) {
            var c, d, e, f = this[0];
            if (!arguments.length) {
                if (f) {
                    return c = p.valHooks[f.type] || p.valHooks[f.nodeName.toLowerCase()], c && "get" in c && (d = c.get(f, "value")) !== b ? d : (d = f.value, typeof d == "string" ? d.replace(P, "") : d == null ? "" : d)
                }
                return
            }
            return e = p.isFunction(a), this.each(function(d) {
                var f, g = p(this);
                if (this.nodeType !== 1) {
                    return
                }
                e ? f = a.call(this, d, g.val()) : f = a, f == null ? f = "" : typeof f == "number" ? f += "" : p.isArray(f) && (f = p.map(f, function(a) {
                    return a == null ? "" : a + ""
                })), c = p.valHooks[this.type] || p.valHooks[this.nodeName.toLowerCase()];
                if (!c || !("set" in c) || c.set(this, f, "value") === b) {
                    this.value = f
                }
            })
        }
    }), p.extend({
        valHooks: {
            option: {
                get: function(a) {
                    var b = a.attributes.value;
                    return !b || b.specified ? a.value : a.text
                }
            },
            select: {
                get: function(a) {
                    var b, c, d, e, f = a.selectedIndex,
                        g = [],
                        h = a.options,
                        i = a.type === "select-one";
                    if (f < 0) {
                        return null
                    }
                    c = i ? f : 0, d = i ? f + 1 : h.length;
                    for (; c < d; c++) {
                        e = h[c];
                        if (e.selected && (p.support.optDisabled ? !e.disabled : e.getAttribute("disabled") === null) && (!e.parentNode.disabled || !p.nodeName(e.parentNode, "optgroup"))) {
                            b = p(e).val();
                            if (i) {
                                return b
                            }
                            g.push(b)
                        }
                    }
                    return i && !g.length && h.length ? p(h[f]).val() : g
                },
                set: function(a, b) {
                    var c = p.makeArray(b);
                    return p(a).find("option").each(function() {
                        this.selected = p.inArray(p(this).val(), c) >= 0
                    }), c.length || (a.selectedIndex = -1), c
                }
            }
        },
        attrFn: {},
        attr: function(a, c, d, e) {
            var f, g, h, i = a.nodeType;
            if (!a || i === 3 || i === 8 || i === 2) {
                return
            }
            if (e && p.isFunction(p.fn[c])) {
                return p(a)[c](d)
            }
            if (typeof a.getAttribute == "undefined") {
                return p.prop(a, c, d)
            }
            h = i !== 1 || !p.isXMLDoc(a), h && (c = c.toLowerCase(), g = p.attrHooks[c] || (T.test(c) ? M : L));
            if (d !== b) {
                if (d === null) {
                    p.removeAttr(a, c);
                    return
                }
                return g && "set" in g && h && (f = g.set(a, d, c)) !== b ? f : (a.setAttribute(c, "" + d), d)
            }
            return g && "get" in g && h && (f = g.get(a, c)) !== null ? f : (f = a.getAttribute(c), f === null ? b : f)
        },
        removeAttr: function(a, b) {
            var c, d, e, f, g = 0;
            if (b && a.nodeType === 1) {
                d = b.split(s);
                for (; g < d.length; g++) {
                    e = d[g], e && (c = p.propFix[e] || e, f = T.test(e), f || p.attr(a, e, ""), a.removeAttribute(U ? e : c), f && c in a && (a[c] = !1))
                }
            }
        },
        attrHooks: {
            type: {
                set: function(a, b) {
                    if (Q.test(a.nodeName) && a.parentNode) {
                        p.error("type property can't be changed")
                    } else {
                        if (!p.support.radioValue && b === "radio" && p.nodeName(a, "input")) {
                            var c = a.value;
                            return a.setAttribute("type", b), c && (a.value = c), b
                        }
                    }
                }
            },
            value: {
                get: function(a, b) {
                    return L && p.nodeName(a, "button") ? L.get(a, b) : b in a ? a.value : null
                },
                set: function(a, b, c) {
                    if (L && p.nodeName(a, "button")) {
                        return L.set(a, b, c)
                    }
                    a.value = b
                }
            }
        },
        propFix: {
            tabindex: "tabIndex",
            readonly: "readOnly",
            "for": "htmlFor",
            "class": "className",
            maxlength: "maxLength",
            cellspacing: "cellSpacing",
            cellpadding: "cellPadding",
            rowspan: "rowSpan",
            colspan: "colSpan",
            usemap: "useMap",
            frameborder: "frameBorder",
            contenteditable: "contentEditable"
        },
        prop: function(a, c, d) {
            var e, f, g, h = a.nodeType;
            if (!a || h === 3 || h === 8 || h === 2) {
                return
            }
            return g = h !== 1 || !p.isXMLDoc(a), g && (c = p.propFix[c] || c, f = p.propHooks[c]), d !== b ? f && "set" in f && (e = f.set(a, d, c)) !== b ? e : a[c] = d : f && "get" in f && (e = f.get(a, c)) !== null ? e : a[c]
        },
        propHooks: {
            tabIndex: {
                get: function(a) {
                    var c = a.getAttributeNode("tabindex");
                    return c && c.specified ? parseInt(c.value, 10) : R.test(a.nodeName) || S.test(a.nodeName) && a.href ? 0 : b
                }
            }
        }
    }), M = {
        get: function(a, c) {
            var d, e = p.prop(a, c);
            return e === !0 || typeof e != "boolean" && (d = a.getAttributeNode(c)) && d.nodeValue !== !1 ? c.toLowerCase() : b
        },
        set: function(a, b, c) {
            var d;
            return b === !1 ? p.removeAttr(a, c) : (d = p.propFix[c] || c, d in a && (a[d] = !0), a.setAttribute(c, c.toLowerCase())), c
        }
    }, U || (N = {
        name: !0,
        id: !0,
        coords: !0
    }, L = p.valHooks.button = {
        get: function(a, c) {
            var d;
            return d = a.getAttributeNode(c), d && (N[c] ? d.value !== "" : d.specified) ? d.value : b
        },
        set: function(a, b, c) {
            var d = a.getAttributeNode(c);
            return d || (d = e.createAttribute(c), a.setAttributeNode(d)), d.value = b + ""
        }
    }, p.each(["width", "height"], function(a, b) {
        p.attrHooks[b] = p.extend(p.attrHooks[b], {
            set: function(a, c) {
                if (c === "") {
                    return a.setAttribute(b, "auto"), c
                }
            }
        })
    }), p.attrHooks.contenteditable = {
        get: L.get,
        set: function(a, b, c) {
            b === "" && (b = "false"), L.set(a, b, c)
        }
    }), p.support.hrefNormalized || p.each(["href", "src", "width", "height"], function(a, c) {
        p.attrHooks[c] = p.extend(p.attrHooks[c], {
            get: function(a) {
                var d = a.getAttribute(c, 2);
                return d === null ? b : d
            }
        })
    }), p.support.style || (p.attrHooks.style = {
        get: function(a) {
            return a.style.cssText.toLowerCase() || b
        },
        set: function(a, b) {
            return a.style.cssText = "" + b
        }
    }), p.support.optSelected || (p.propHooks.selected = p.extend(p.propHooks.selected, {
        get: function(a) {
            var b = a.parentNode;
            return b && (b.selectedIndex, b.parentNode && b.parentNode.selectedIndex), null
        }
    })), p.support.enctype || (p.propFix.enctype = "encoding"), p.support.checkOn || p.each(["radio", "checkbox"], function() {
        p.valHooks[this] = {
            get: function(a) {
                return a.getAttribute("value") === null ? "on" : a.value
            }
        }
    }), p.each(["radio", "checkbox"], function() {
        p.valHooks[this] = p.extend(p.valHooks[this], {
            set: function(a, b) {
                if (p.isArray(b)) {
                    return a.checked = p.inArray(p(a).val(), b) >= 0
                }
            }
        })
    });
    var V = /^(?:textarea|input|select)$/i,
        W = /^([^\.]*|)(?:\.(.+)|)$/,
        X = /(?:^|\s)hover(\.\S+|)\b/,
        Y = /^key/,
        Z = /^(?:mouse|contextmenu)|click/,
        $ = /^(?:focusinfocus|focusoutblur)$/,
        _ = function(a) {
            return p.event.special.hover ? a : a.replace(X, "mouseenter$1 mouseleave$1")
        };
    p.event = {
        add: function(a, c, d, e, f) {
            var g, h, i, j, k, l, m, n, o, q, r;
            if (a.nodeType === 3 || a.nodeType === 8 || !c || !d || !(g = p._data(a))) {
                return
            }
            d.handler && (o = d, d = o.handler, f = o.selector), d.guid || (d.guid = p.guid++), i = g.events, i || (g.events = i = {}), h = g.handle, h || (g.handle = h = function(a) {
                return typeof p != "undefined" && (!a || p.event.triggered !== a.type) ? p.event.dispatch.apply(h.elem, arguments) : b
            }, h.elem = a), c = p.trim(_(c)).split(" ");
            for (j = 0; j < c.length; j++) {
                k = W.exec(c[j]) || [], l = k[1], m = (k[2] || "").split(".").sort(), r = p.event.special[l] || {}, l = (f ? r.delegateType : r.bindType) || l, r = p.event.special[l] || {}, n = p.extend({
                    type: l,
                    origType: k[1],
                    data: e,
                    handler: d,
                    guid: d.guid,
                    selector: f,
                    namespace: m.join(".")
                }, o), q = i[l];
                if (!q) {
                    q = i[l] = [], q.delegateCount = 0;
                    if (!r.setup || r.setup.call(a, e, m, h) === !1) {
                        a.addEventListener ? a.addEventListener(l, h, !1) : a.attachEvent && a.attachEvent("on" + l, h)
                    }
                }
                r.add && (r.add.call(a, n), n.handler.guid || (n.handler.guid = d.guid)), f ? q.splice(q.delegateCount++, 0, n) : q.push(n), p.event.global[l] = !0
            }
            a = null
        },
        global: {},
        remove: function(a, b, c, d, e) {
            var f, g, h, i, j, k, l, m, n, o, q, r = p.hasData(a) && p._data(a);
            if (!r || !(m = r.events)) {
                return
            }
            b = p.trim(_(b || "")).split(" ");
            for (f = 0; f < b.length; f++) {
                g = W.exec(b[f]) || [], h = i = g[1], j = g[2];
                if (!h) {
                    for (h in m) {
                        p.event.remove(a, h + b[f], c, d, !0)
                    }
                    continue
                }
                n = p.event.special[h] || {}, h = (d ? n.delegateType : n.bindType) || h, o = m[h] || [], k = o.length, j = j ? new RegExp("(^|\\.)" + j.split(".").sort().join("\\.(?:.*\\.|)") + "(\\.|$)") : null;
                for (l = 0; l < o.length; l++) {
                    q = o[l], (e || i === q.origType) && (!c || c.guid === q.guid) && (!j || j.test(q.namespace)) && (!d || d === q.selector || d === "**" && q.selector) && (o.splice(l--, 1), q.selector && o.delegateCount--, n.remove && n.remove.call(a, q))
                }
                o.length === 0 && k !== o.length && ((!n.teardown || n.teardown.call(a, j, r.handle) === !1) && p.removeEvent(a, h, r.handle), delete m[h])
            }
            p.isEmptyObject(m) && (delete r.handle, p.removeData(a, "events", !0))
        },
        customEvent: {
            getData: !0,
            setData: !0,
            changeData: !0
        },
        trigger: function(c, d, f, g) {
            if (!f || f.nodeType !== 3 && f.nodeType !== 8) {
                var h, i, j, k, l, m, n, o, q, r, s = c.type || c,
                    t = [];
                if ($.test(s + p.event.triggered)) {
                    return
                }
                s.indexOf("!") >= 0 && (s = s.slice(0, -1), i = !0), s.indexOf(".") >= 0 && (t = s.split("."), s = t.shift(), t.sort());
                if ((!f || p.event.customEvent[s]) && !p.event.global[s]) {
                    return
                }
                c = typeof c == "object" ? c[p.expando] ? c : new p.Event(s, c) : new p.Event(s), c.type = s, c.isTrigger = !0, c.exclusive = i, c.namespace = t.join("."), c.namespace_re = c.namespace ? new RegExp("(^|\\.)" + t.join("\\.(?:.*\\.|)") + "(\\.|$)") : null, m = s.indexOf(":") < 0 ? "on" + s : "";
                if (!f) {
                    h = p.cache;
                    for (j in h) {
                        h[j].events && h[j].events[s] && p.event.trigger(c, d, h[j].handle.elem, !0)
                    }
                    return
                }
                c.result = b, c.target || (c.target = f), d = d != null ? p.makeArray(d) : [], d.unshift(c), n = p.event.special[s] || {};
                if (n.trigger && n.trigger.apply(f, d) === !1) {
                    return
                }
                q = [
                    [f, n.bindType || s]
                ];
                if (!g && !n.noBubble && !p.isWindow(f)) {
                    r = n.delegateType || s, k = $.test(r + s) ? f : f.parentNode;
                    for (l = f; k; k = k.parentNode) {
                        q.push([k, r]), l = k
                    }
                    l === (f.ownerDocument || e) && q.push([l.defaultView || l.parentWindow || a, r])
                }
                for (j = 0; j < q.length && !c.isPropagationStopped(); j++) {
                    k = q[j][0], c.type = q[j][1], o = (p._data(k, "events") || {})[c.type] && p._data(k, "handle"), o && o.apply(k, d), o = m && k[m], o && p.acceptData(k) && o.apply(k, d) === !1 && c.preventDefault()
                }
                return c.type = s, !g && !c.isDefaultPrevented() && (!n._default || n._default.apply(f.ownerDocument, d) === !1) && (s !== "click" || !p.nodeName(f, "a")) && p.acceptData(f) && m && f[s] && (s !== "focus" && s !== "blur" || c.target.offsetWidth !== 0) && !p.isWindow(f) && (l = f[m], l && (f[m] = null), p.event.triggered = s, f[s](), p.event.triggered = b, l && (f[m] = l)), c.result
            }
            return
        },
        dispatch: function(c) {
            c = p.event.fix(c || a.event);
            var d, e, f, g, h, i, j, k, l, m, n = (p._data(this, "events") || {})[c.type] || [],
                o = n.delegateCount,
                q = [].slice.call(arguments),
                r = !c.exclusive && !c.namespace,
                s = p.event.special[c.type] || {}, t = [];
            q[0] = c, c.delegateTarget = this;
            if (s.preDispatch && s.preDispatch.call(this, c) === !1) {
                return
            }
            if (o && (!c.button || c.type !== "click")) {
                for (f = c.target; f != this; f = f.parentNode || this) {
                    if (f.disabled !== !0 || c.type !== "click") {
                        h = {}, j = [];
                        for (d = 0; d < o; d++) {
                            k = n[d], l = k.selector, h[l] === b && (h[l] = p(l, this).index(f) >= 0), h[l] && j.push(k)
                        }
                        j.length && t.push({
                            elem: f,
                            matches: j
                        })
                    }
                }
            }
            n.length > o && t.push({
                elem: this,
                matches: n.slice(o)
            });
            for (d = 0; d < t.length && !c.isPropagationStopped(); d++) {
                i = t[d], c.currentTarget = i.elem;
                for (e = 0; e < i.matches.length && !c.isImmediatePropagationStopped(); e++) {
                    k = i.matches[e];
                    if (r || !c.namespace && !k.namespace || c.namespace_re && c.namespace_re.test(k.namespace)) {
                        c.data = k.data, c.handleObj = k, g = ((p.event.special[k.origType] || {}).handle || k.handler).apply(i.elem, q), g !== b && (c.result = g, g === !1 && (c.preventDefault(), c.stopPropagation()))
                    }
                }
            }
            return s.postDispatch && s.postDispatch.call(this, c), c.result
        },
        props: "attrChange attrName relatedNode srcElement altKey bubbles cancelable ctrlKey currentTarget eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),
        fixHooks: {},
        keyHooks: {
            props: "char charCode key keyCode".split(" "),
            filter: function(a, b) {
                return a.which == null && (a.which = b.charCode != null ? b.charCode : b.keyCode), a
            }
        },
        mouseHooks: {
            props: "button buttons clientX clientY fromElement offsetX offsetY pageX pageY screenX screenY toElement".split(" "),
            filter: function(a, c) {
                var d, f, g, h = c.button,
                    i = c.fromElement;
                return a.pageX == null && c.clientX != null && (d = a.target.ownerDocument || e, f = d.documentElement, g = d.body, a.pageX = c.clientX + (f && f.scrollLeft || g && g.scrollLeft || 0) - (f && f.clientLeft || g && g.clientLeft || 0), a.pageY = c.clientY + (f && f.scrollTop || g && g.scrollTop || 0) - (f && f.clientTop || g && g.clientTop || 0)), !a.relatedTarget && i && (a.relatedTarget = i === a.target ? c.toElement : i), !a.which && h !== b && (a.which = h & 1 ? 1 : h & 2 ? 3 : h & 4 ? 2 : 0), a
            }
        },
        fix: function(a) {
            if (a[p.expando]) {
                return a
            }
            var b, c, d = a,
                f = p.event.fixHooks[a.type] || {}, g = f.props ? this.props.concat(f.props) : this.props;
            a = p.Event(d);
            for (b = g.length; b;) {
                c = g[--b], a[c] = d[c]
            }
            return a.target || (a.target = d.srcElement || e), a.target.nodeType === 3 && (a.target = a.target.parentNode), a.metaKey = !! a.metaKey, f.filter ? f.filter(a, d) : a
        },
        special: {
            load: {
                noBubble: !0
            },
            focus: {
                delegateType: "focusin"
            },
            blur: {
                delegateType: "focusout"
            },
            beforeunload: {
                setup: function(a, b, c) {
                    p.isWindow(this) && (this.onbeforeunload = c)
                },
                teardown: function(a, b) {
                    this.onbeforeunload === b && (this.onbeforeunload = null)
                }
            }
        },
        simulate: function(a, b, c, d) {
            var e = p.extend(new p.Event, c, {
                type: a,
                isSimulated: !0,
                originalEvent: {}
            });
            d ? p.event.trigger(e, null, b) : p.event.dispatch.call(b, e), e.isDefaultPrevented() && c.preventDefault()
        }
    }, p.event.handle = p.event.dispatch, p.removeEvent = e.removeEventListener ? function(a, b, c) {
        a.removeEventListener && a.removeEventListener(b, c, !1)
    } : function(a, b, c) {
        var d = "on" + b;
        a.detachEvent && (typeof a[d] == "undefined" && (a[d] = null), a.detachEvent(d, c))
    }, p.Event = function(a, b) {
        if (this instanceof p.Event) {
            a && a.type ? (this.originalEvent = a, this.type = a.type, this.isDefaultPrevented = a.defaultPrevented || a.returnValue === !1 || a.getPreventDefault && a.getPreventDefault() ? bb : ba) : this.type = a, b && p.extend(this, b), this.timeStamp = a && a.timeStamp || p.now(), this[p.expando] = !0
        } else {
            return new p.Event(a, b)
        }
    }, p.Event.prototype = {
        preventDefault: function() {
            this.isDefaultPrevented = bb;
            var a = this.originalEvent;
            if (!a) {
                return
            }
            a.preventDefault ? a.preventDefault() : a.returnValue = !1
        },
        stopPropagation: function() {
            this.isPropagationStopped = bb;
            var a = this.originalEvent;
            if (!a) {
                return
            }
            a.stopPropagation && a.stopPropagation(), a.cancelBubble = !0
        },
        stopImmediatePropagation: function() {
            this.isImmediatePropagationStopped = bb, this.stopPropagation()
        },
        isDefaultPrevented: ba,
        isPropagationStopped: ba,
        isImmediatePropagationStopped: ba
    }, p.each({
        mouseenter: "mouseover",
        mouseleave: "mouseout"
    }, function(a, b) {
        p.event.special[a] = {
            delegateType: b,
            bindType: b,
            handle: function(a) {
                var c, d = this,
                    e = a.relatedTarget,
                    f = a.handleObj,
                    g = f.selector;
                if (!e || e !== d && !p.contains(d, e)) {
                    a.type = f.origType, c = f.handler.apply(this, arguments), a.type = b
                }
                return c
            }
        }
    }), p.support.submitBubbles || (p.event.special.submit = {
        setup: function() {
            if (p.nodeName(this, "form")) {
                return !1
            }
            p.event.add(this, "click._submit keypress._submit", function(a) {
                var c = a.target,
                    d = p.nodeName(c, "input") || p.nodeName(c, "button") ? c.form : b;
                d && !p._data(d, "_submit_attached") && (p.event.add(d, "submit._submit", function(a) {
                    a._submit_bubble = !0
                }), p._data(d, "_submit_attached", !0))
            })
        },
        postDispatch: function(a) {
            a._submit_bubble && (delete a._submit_bubble, this.parentNode && !a.isTrigger && p.event.simulate("submit", this.parentNode, a, !0))
        },
        teardown: function() {
            if (p.nodeName(this, "form")) {
                return !1
            }
            p.event.remove(this, "._submit")
        }
    }), p.support.changeBubbles || (p.event.special.change = {
        setup: function() {
            if (V.test(this.nodeName)) {
                if (this.type === "checkbox" || this.type === "radio") {
                    p.event.add(this, "propertychange._change", function(a) {
                        a.originalEvent.propertyName === "checked" && (this._just_changed = !0)
                    }), p.event.add(this, "click._change", function(a) {
                        this._just_changed && !a.isTrigger && (this._just_changed = !1), p.event.simulate("change", this, a, !0)
                    })
                }
                return !1
            }
            p.event.add(this, "beforeactivate._change", function(a) {
                var b = a.target;
                V.test(b.nodeName) && !p._data(b, "_change_attached") && (p.event.add(b, "change._change", function(a) {
                    this.parentNode && !a.isSimulated && !a.isTrigger && p.event.simulate("change", this.parentNode, a, !0)
                }), p._data(b, "_change_attached", !0))
            })
        },
        handle: function(a) {
            var b = a.target;
            if (this !== b || a.isSimulated || a.isTrigger || b.type !== "radio" && b.type !== "checkbox") {
                return a.handleObj.handler.apply(this, arguments)
            }
        },
        teardown: function() {
            return p.event.remove(this, "._change"), !V.test(this.nodeName)
        }
    }), p.support.focusinBubbles || p.each({
        focus: "focusin",
        blur: "focusout"
    }, function(a, b) {
        var c = 0,
            d = function(a) {
                p.event.simulate(b, a.target, p.event.fix(a), !0)
            };
        p.event.special[b] = {
            setup: function() {
                c++ === 0 && e.addEventListener(a, d, !0)
            },
            teardown: function() {
                --c === 0 && e.removeEventListener(a, d, !0)
            }
        }
    }), p.fn.extend({
        on: function(a, c, d, e, f) {
            var g, h;
            if (typeof a == "object") {
                typeof c != "string" && (d = d || c, c = b);
                for (h in a) {
                    this.on(h, c, d, a[h], f)
                }
                return this
            }
            d == null && e == null ? (e = c, d = c = b) : e == null && (typeof c == "string" ? (e = d, d = b) : (e = d, d = c, c = b));
            if (e === !1) {
                e = ba
            } else {
                if (!e) {
                    return this
                }
            }
            return f === 1 && (g = e, e = function(a) {
                return p().off(a), g.apply(this, arguments)
            }, e.guid = g.guid || (g.guid = p.guid++)), this.each(function() {
                p.event.add(this, a, e, d, c)
            })
        },
        one: function(a, b, c, d) {
            return this.on(a, b, c, d, 1)
        },
        off: function(a, c, d) {
            var e, f;
            if (a && a.preventDefault && a.handleObj) {
                return e = a.handleObj, p(a.delegateTarget).off(e.namespace ? e.origType + "." + e.namespace : e.origType, e.selector, e.handler), this
            }
            if (typeof a == "object") {
                for (f in a) {
                    this.off(f, c, a[f])
                }
                return this
            }
            if (c === !1 || typeof c == "function") {
                d = c, c = b
            }
            return d === !1 && (d = ba), this.each(function() {
                p.event.remove(this, a, d, c)
            })
        },
        bind: function(a, b, c) {
            return this.on(a, null, b, c)
        },
        unbind: function(a, b) {
            return this.off(a, null, b)
        },
        live: function(a, b, c) {
            return p(this.context).on(a, this.selector, b, c), this
        },
        die: function(a, b) {
            return p(this.context).off(a, this.selector || "**", b), this
        },
        delegate: function(a, b, c, d) {
            return this.on(b, a, c, d)
        },
        undelegate: function(a, b, c) {
            return arguments.length == 1 ? this.off(a, "**") : this.off(b, a || "**", c)
        },
        trigger: function(a, b) {
            return this.each(function() {
                p.event.trigger(a, b, this)
            })
        },
        triggerHandler: function(a, b) {
            if (this[0]) {
                return p.event.trigger(a, b, this[0], !0)
            }
        },
        toggle: function(a) {
            var b = arguments,
                c = a.guid || p.guid++,
                d = 0,
                e = function(c) {
                    var e = (p._data(this, "lastToggle" + a.guid) || 0) % d;
                    return p._data(this, "lastToggle" + a.guid, e + 1), c.preventDefault(), b[e].apply(this, arguments) || !1
                };
            e.guid = c;
            while (d < b.length) {
                b[d++].guid = c
            }
            return this.click(e)
        },
        hover: function(a, b) {
            return this.mouseenter(a).mouseleave(b || a)
        }
    }), p.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "), function(a, b) {
        p.fn[b] = function(a, c) {
            return c == null && (c = a, a = null), arguments.length > 0 ? this.on(b, null, a, c) : this.trigger(b)
        }, Y.test(b) && (p.event.fixHooks[b] = p.event.keyHooks), Z.test(b) && (p.event.fixHooks[b] = p.event.mouseHooks)
    }),
    function(a, b) {
        function $(a, b, c, d) {
            c = c || [], b = b || q;
            var e, f, g, j, k = b.nodeType;
            if (k !== 1 && k !== 9) {
                return []
            }
            if (!a || typeof a != "string") {
                return c
            }
            g = h(b);
            if (!g && !d) {
                if (e = L.exec(a)) {
                    if (j = e[1]) {
                        if (k === 9) {
                            f = b.getElementById(j);
                            if (!f || !f.parentNode) {
                                return c
                            }
                            if (f.id === j) {
                                return c.push(f), c
                            }
                        } else {
                            if (b.ownerDocument && (f = b.ownerDocument.getElementById(j)) && i(b, f) && f.id === j) {
                                return c.push(f), c
                            }
                        }
                    } else {
                        if (e[2]) {
                            return u.apply(c, t.call(b.getElementsByTagName(a), 0)), c
                        }
                        if ((j = e[3]) && X && b.getElementsByClassName) {
                            return u.apply(c, t.call(b.getElementsByClassName(j), 0)), c
                        }
                    }
                }
            }
            return bk(a, b, c, d, g)
        }

        function _(a) {
            return function(b) {
                var c = b.nodeName.toLowerCase();
                return c === "input" && b.type === a
            }
        }

        function ba(a) {
            return function(b) {
                var c = b.nodeName.toLowerCase();
                return (c === "input" || c === "button") && b.type === a
            }
        }

        function bb(a, b, c) {
            if (a === b) {
                return c
            }
            var d = a.nextSibling;
            while (d) {
                if (d === b) {
                    return -1
                }
                d = d.nextSibling
            }
            return 1
        }

        function bc(a, b, c, d) {
            var e, g, h, i, j, k, l, m, n, p, r = !c && b !== q,
                s = (r ? "<s>" : "") + a.replace(H, "$1<s>"),
                u = y[o][s];
            if (u) {
                return d ? 0 : t.call(u, 0)
            }
            j = a, k = [], m = 0, n = f.preFilter, p = f.filter;
            while (j) {
                if (!e || (g = I.exec(j))) {
                    g && (j = j.slice(g[0].length), h.selector = l), k.push(h = []), l = "", r && (j = " " + j)
                }
                e = !1;
                if (g = J.exec(j)) {
                    l += g[0], j = j.slice(g[0].length), e = h.push({
                        part: g.pop().replace(H, " "),
                        string: g[0],
                        captures: g
                    })
                }
                for (i in p) {
                    (g = S[i].exec(j)) && (!n[i] || (g = n[i](g, b, c))) && (l += g[0], j = j.slice(g[0].length), e = h.push({
                        part: i,
                        string: g.shift(),
                        captures: g
                    }))
                }
                if (!e) {
                    break
                }
            }
            return l && (h.selector = l), d ? j.length : j ? $.error(a) : t.call(y(s, k), 0)
        }

        function bd(a, b, e, f) {
            var g = b.dir,
                h = s++;
            return a || (a = function(a) {
                return a === e
            }), b.first ? function(b) {
                while (b = b[g]) {
                    if (b.nodeType === 1) {
                        return a(b) && b
                    }
                }
            } : f ? function(b) {
                while (b = b[g]) {
                    if (b.nodeType === 1 && a(b)) {
                        return b
                    }
                }
            } : function(b) {
                var e, f = h + "." + c,
                    i = f + "." + d;
                while (b = b[g]) {
                    if (b.nodeType === 1) {
                        if ((e = b[o]) === i) {
                            return b.sizset
                        }
                        if (typeof e == "string" && e.indexOf(f) === 0) {
                            if (b.sizset) {
                                return b
                            }
                        } else {
                            b[o] = i;
                            if (a(b)) {
                                return b.sizset = !0, b
                            }
                            b.sizset = !1
                        }
                    }
                }
            }
        }

        function be(a, b) {
            return a ? function(c) {
                var d = b(c);
                return d && a(d === !0 ? c : d)
            } : b
        }

        function bf(a, b, c) {
            var d, e, g = 0;
            for (; d = a[g]; g++) {
                f.relative[d.part] ? e = bd(e, f.relative[d.part], b, c) : e = be(e, f.filter[d.part].apply(null, d.captures.concat(b, c)))
            }
            return e
        }

        function bg(a) {
            return function(b) {
                var c, d = 0;
                for (; c = a[d]; d++) {
                    if (c(b)) {
                        return !0
                    }
                }
                return !1
            }
        }

        function bh(a, b, c, d) {
            var e = 0,
                f = b.length;
            for (; e < f; e++) {
                $(a, b[e], c, d)
            }
        }

        function bi(a, b, c, d, e, g) {
            var h, i = f.setFilters[b.toLowerCase()];
            return i || $.error(b), (a || !(h = e)) && bh(a || "*", d, h = [], e), h.length > 0 ? i(h, c, g) : []
        }

        function bj(a, c, d, e) {
            var f, g, h, i, j, k, l, m, n, o, p, q, r, s = 0,
                t = a.length,
                v = S.POS,
                w = new RegExp("^" + v.source + "(?!" + A + ")", "i"),
                x = function() {
                    var a = 1,
                        c = arguments.length - 2;
                    for (; a < c; a++) {
                        arguments[a] === b && (n[a] = b)
                    }
                };
            for (; s < t; s++) {
                f = a[s], g = "", m = e;
                for (h = 0, i = f.length; h < i; h++) {
                    j = f[h], k = j.string;
                    if (j.part === "PSEUDO") {
                        v.exec(""), l = 0;
                        while (n = v.exec(k)) {
                            o = !0, p = v.lastIndex = n.index + n[0].length;
                            if (p > l) {
                                g += k.slice(l, n.index), l = p, q = [c], J.test(g) && (m && (q = m), m = e);
                                if (r = O.test(g)) {
                                    g = g.slice(0, -5).replace(J, "$&*"), l++
                                }
                                n.length > 1 && n[0].replace(w, x), m = bi(g, n[1], n[2], q, m, r)
                            }
                            g = ""
                        }
                    }
                    o || (g += k), o = !1
                }
                g ? J.test(g) ? bh(g, m || [c], d, e) : $(g, c, d, e ? e.concat(m) : m) : u.apply(d, m)
            }
            return t === 1 ? d : $.uniqueSort(d)
        }

        function bk(a, b, e, g, h) {
            a = a.replace(H, "$1");
            var i, k, l, m, n, o, p, q, r, s, v = bc(a, b, h),
                w = b.nodeType;
            if (S.POS.test(a)) {
                return bj(v, b, e, g)
            }
            if (g) {
                i = t.call(g, 0)
            } else {
                if (v.length === 1) {
                    if ((o = t.call(v[0], 0)).length > 2 && (p = o[0]).part === "ID" && w === 9 && !h && f.relative[o[1].part]) {
                        b = f.find.ID(p.captures[0].replace(R, ""), b, h)[0];
                        if (!b) {
                            return e
                        }
                        a = a.slice(o.shift().string.length)
                    }
                    r = (v = N.exec(o[0].string)) && !v.index && b.parentNode || b, q = "";
                    for (n = o.length - 1; n >= 0; n--) {
                        p = o[n], s = p.part, q = p.string + q;
                        if (f.relative[s]) {
                            break
                        }
                        if (f.order.test(s)) {
                            i = f.find[s](p.captures[0].replace(R, ""), r, h);
                            if (i == null) {
                                continue
                            }
                            a = a.slice(0, a.length - q.length) + q.replace(S[s], ""), a || u.apply(e, t.call(i, 0));
                            break
                        }
                    }
                }
            } if (a) {
                k = j(a, b, h), c = k.dirruns++, i == null && (i = f.find.TAG("*", N.test(a) && b.parentNode || b));
                for (n = 0; m = i[n]; n++) {
                    d = k.runs++, k(m) && e.push(m)
                }
            }
            return e
        }
        var c, d, e, f, g, h, i, j, k, l, m = !0,
            n = "undefined",
            o = ("sizcache" + Math.random()).replace(".", ""),
            q = a.document,
            r = q.documentElement,
            s = 0,
            t = [].slice,
            u = [].push,
            v = function(a, b) {
                return a[o] = b || !0, a
            }, w = function() {
                var a = {}, b = [];
                return v(function(c, d) {
                    return b.push(c) > f.cacheLength && delete a[b.shift()], a[c] = d
                }, a)
            }, x = w(),
            y = w(),
            z = w(),
            A = "[\\x20\\t\\r\\n\\f]",
            B = "(?:\\\\.|[-\\w]|[^\\x00-\\xa0])+",
            C = B.replace("w", "w#"),
            D = "([*^$|!~]?=)",
            E = "\\[" + A + "*(" + B + ")" + A + "*(?:" + D + A + "*(?:(['\"])((?:\\\\.|[^\\\\])*?)\\3|(" + C + ")|)|)" + A + "*\\]",
            F = ":(" + B + ")(?:\\((?:(['\"])((?:\\\\.|[^\\\\])*?)\\2|([^()[\\]]*|(?:(?:" + E + ")|[^:]|\\\\.)*|.*))\\)|)",
            G = ":(nth|eq|gt|lt|first|last|even|odd)(?:\\(((?:-\\d)?\\d*)\\)|)(?=[^-]|$)",
            H = new RegExp("^" + A + "+|((?:^|[^\\\\])(?:\\\\.)*)" + A + "+$", "g"),
            I = new RegExp("^" + A + "*," + A + "*"),
            J = new RegExp("^" + A + "*([\\x20\\t\\r\\n\\f>+~])" + A + "*"),
            K = new RegExp(F),
            L = /^(?:#([\w\-]+)|(\w+)|\.([\w\-]+))$/,
            M = /^:not/,
            N = /[\x20\t\r\n\f]*[+~]/,
            O = /:not\($/,
            P = /h\d/i,
            Q = /input|select|textarea|button/i,
            R = /\\(?!\\)/g,
            S = {
                ID: new RegExp("^#(" + B + ")"),
                CLASS: new RegExp("^\\.(" + B + ")"),
                NAME: new RegExp("^\\[name=['\"]?(" + B + ")['\"]?\\]"),
                TAG: new RegExp("^(" + B.replace("w", "w*") + ")"),
                ATTR: new RegExp("^" + E),
                PSEUDO: new RegExp("^" + F),
                CHILD: new RegExp("^:(only|nth|last|first)-child(?:\\(" + A + "*(even|odd|(([+-]|)(\\d*)n|)" + A + "*(?:([+-]|)" + A + "*(\\d+)|))" + A + "*\\)|)", "i"),
                POS: new RegExp(G, "ig"),
                needsContext: new RegExp("^" + A + "*[>+~]|" + G, "i")
            }, T = function(a) {
                var b = q.createElement("div");
                try {
                    return a(b)
                } catch (c) {
                    return !1
                } finally {
                    b = null
                }
            }, U = T(function(a) {
                return a.appendChild(q.createComment("")), !a.getElementsByTagName("*").length
            }),
            V = T(function(a) {
                return a.innerHTML = "<a href='#'></a>", a.firstChild && typeof a.firstChild.getAttribute !== n && a.firstChild.getAttribute("href") === "#"
            }),
            W = T(function(a) {
                a.innerHTML = "<select></select>";
                var b = typeof a.lastChild.getAttribute("multiple");
                return b !== "boolean" && b !== "string"
            }),
            X = T(function(a) {
                return a.innerHTML = "<div class='hidden e'></div><div class='hidden'></div>", !a.getElementsByClassName || !a.getElementsByClassName("e").length ? !1 : (a.lastChild.className = "e", a.getElementsByClassName("e").length === 2)
            }),
            Y = T(function(a) {
                a.id = o + 0, a.innerHTML = "<a name='" + o + "'></a><div name='" + o + "'></div>", r.insertBefore(a, r.firstChild);
                var b = q.getElementsByName && q.getElementsByName(o).length === 2 + q.getElementsByName(o + 0).length;
                return e = !q.getElementById(o), r.removeChild(a), b
            });
        try {
            t.call(r.childNodes, 0)[0].nodeType
        } catch (Z) {
            t = function(a) {
                var b, c = [];
                for (; b = this[a]; a++) {
                    c.push(b)
                }
                return c
            }
        }
        $.matches = function(a, b) {
            return $(a, null, null, b)
        }, $.matchesSelector = function(a, b) {
            return $(b, null, null, [a]).length > 0
        }, g = $.getText = function(a) {
            var b, c = "",
                d = 0,
                e = a.nodeType;
            if (e) {
                if (e === 1 || e === 9 || e === 11) {
                    if (typeof a.textContent == "string") {
                        return a.textContent
                    }
                    for (a = a.firstChild; a; a = a.nextSibling) {
                        c += g(a)
                    }
                } else {
                    if (e === 3 || e === 4) {
                        return a.nodeValue
                    }
                }
            } else {
                for (; b = a[d]; d++) {
                    c += g(b)
                }
            }
            return c
        }, h = $.isXML = function(a) {
            var b = a && (a.ownerDocument || a).documentElement;
            return b ? b.nodeName !== "HTML" : !1
        }, i = $.contains = r.contains ? function(a, b) {
            var c = a.nodeType === 9 ? a.documentElement : a,
                d = b && b.parentNode;
            return a === d || !! (d && d.nodeType === 1 && c.contains && c.contains(d))
        } : r.compareDocumentPosition ? function(a, b) {
            return b && !! (a.compareDocumentPosition(b) & 16)
        } : function(a, b) {
            while (b = b.parentNode) {
                if (b === a) {
                    return !0
                }
            }
            return !1
        }, $.attr = function(a, b) {
            var c, d = h(a);
            return d || (b = b.toLowerCase()), f.attrHandle[b] ? f.attrHandle[b](a) : W || d ? a.getAttribute(b) : (c = a.getAttributeNode(b), c ? typeof a[b] == "boolean" ? a[b] ? b : null : c.specified ? c.value : null : null)
        }, f = $.selectors = {
            cacheLength: 50,
            createPseudo: v,
            match: S,
            order: new RegExp("ID|TAG" + (Y ? "|NAME" : "") + (X ? "|CLASS" : "")),
            attrHandle: V ? {} : {
                href: function(a) {
                    return a.getAttribute("href", 2)
                },
                type: function(a) {
                    return a.getAttribute("type")
                }
            },
            find: {
                ID: e ? function(a, b, c) {
                    if (typeof b.getElementById !== n && !c) {
                        var d = b.getElementById(a);
                        return d && d.parentNode ? [d] : []
                    }
                } : function(a, c, d) {
                    if (typeof c.getElementById !== n && !d) {
                        var e = c.getElementById(a);
                        return e ? e.id === a || typeof e.getAttributeNode !== n && e.getAttributeNode("id").value === a ? [e] : b : []
                    }
                },
                TAG: U ? function(a, b) {
                    if (typeof b.getElementsByTagName !== n) {
                        return b.getElementsByTagName(a)
                    }
                } : function(a, b) {
                    var c = b.getElementsByTagName(a);
                    if (a === "*") {
                        var d, e = [],
                            f = 0;
                        for (; d = c[f]; f++) {
                            d.nodeType === 1 && e.push(d)
                        }
                        return e
                    }
                    return c
                },
                NAME: function(a, b) {
                    if (typeof b.getElementsByName !== n) {
                        return b.getElementsByName(name)
                    }
                },
                CLASS: function(a, b, c) {
                    if (typeof b.getElementsByClassName !== n && !c) {
                        return b.getElementsByClassName(a)
                    }
                }
            },
            relative: {
                ">": {
                    dir: "parentNode",
                    first: !0
                },
                " ": {
                    dir: "parentNode"
                },
                "+": {
                    dir: "previousSibling",
                    first: !0
                },
                "~": {
                    dir: "previousSibling"
                }
            },
            preFilter: {
                ATTR: function(a) {
                    return a[1] = a[1].replace(R, ""), a[3] = (a[4] || a[5] || "").replace(R, ""), a[2] === "~=" && (a[3] = " " + a[3] + " "), a.slice(0, 4)
                },
                CHILD: function(a) {
                    return a[1] = a[1].toLowerCase(), a[1] === "nth" ? (a[2] || $.error(a[0]), a[3] = +(a[3] ? a[4] + (a[5] || 1) : 2 * (a[2] === "even" || a[2] === "odd")), a[4] = +(a[6] + a[7] || a[2] === "odd")) : a[2] && $.error(a[0]), a
                },
                PSEUDO: function(a, b, c) {
                    var d, e;
                    if (S.CHILD.test(a[0])) {
                        return null
                    }
                    if (a[3]) {
                        a[2] = a[3]
                    } else {
                        if (d = a[4]) {
                            K.test(d) && (e = bc(d, b, c, !0)) && (e = d.indexOf(")", d.length - e) - d.length) && (d = d.slice(0, e), a[0] = a[0].slice(0, e)), a[2] = d
                        }
                    }
                    return a.slice(0, 3)
                }
            },
            filter: {
                ID: e ? function(a) {
                    return a = a.replace(R, ""),
                    function(b) {
                        return b.getAttribute("id") === a
                    }
                } : function(a) {
                    return a = a.replace(R, ""),
                    function(b) {
                        var c = typeof b.getAttributeNode !== n && b.getAttributeNode("id");
                        return c && c.value === a
                    }
                },
                TAG: function(a) {
                    return a === "*" ? function() {
                        return !0
                    } : (a = a.replace(R, "").toLowerCase(), function(b) {
                        return b.nodeName && b.nodeName.toLowerCase() === a
                    })
                },
                CLASS: function(a) {
                    var b = x[o][a];
                    return b || (b = x(a, new RegExp("(^|" + A + ")" + a + "(" + A + "|$)"))),
                    function(a) {
                        return b.test(a.className || typeof a.getAttribute !== n && a.getAttribute("class") || "")
                    }
                },
                ATTR: function(a, b, c) {
                    return b ? function(d) {
                        var e = $.attr(d, a),
                            f = e + "";
                        if (e == null) {
                            return b === "!="
                        }
                        switch (b) {
                            case "=":
                                return f === c;
                            case "!=":
                                return f !== c;
                            case "^=":
                                return c && f.indexOf(c) === 0;
                            case "*=":
                                return c && f.indexOf(c) > -1;
                            case "$=":
                                return c && f.substr(f.length - c.length) === c;
                            case "~=":
                                return (" " + f + " ").indexOf(c) > -1;
                            case "|=":
                                return f === c || f.substr(0, c.length + 1) === c + "-"
                        }
                    } : function(b) {
                        return $.attr(b, a) != null
                    }
                },
                CHILD: function(a, b, c, d) {
                    if (a === "nth") {
                        var e = s++;
                        return function(a) {
                            var b, f, g = 0,
                                h = a;
                            if (c === 1 && d === 0) {
                                return !0
                            }
                            b = a.parentNode;
                            if (b && (b[o] !== e || !a.sizset)) {
                                for (h = b.firstChild; h; h = h.nextSibling) {
                                    if (h.nodeType === 1) {
                                        h.sizset = ++g;
                                        if (h === a) {
                                            break
                                        }
                                    }
                                }
                                b[o] = e
                            }
                            return f = a.sizset - d, c === 0 ? f === 0 : f % c === 0 && f / c >= 0
                        }
                    }
                    return function(b) {
                        var c = b;
                        switch (a) {
                            case "only":
                            case "first":
                                while (c = c.previousSibling) {
                                    if (c.nodeType === 1) {
                                        return !1
                                    }
                                }
                                if (a === "first") {
                                    return !0
                                }
                                c = b;
                            case "last":
                                while (c = c.nextSibling) {
                                    if (c.nodeType === 1) {
                                        return !1
                                    }
                                }
                                return !0
                        }
                    }
                },
                PSEUDO: function(a, b, c, d) {
                    var e, g = f.pseudos[a] || f.pseudos[a.toLowerCase()];
                    return g || $.error("unsupported pseudo: " + a), g[o] ? g(b, c, d) : g.length > 1 ? (e = [a, a, "", b], function(a) {
                        return g(a, 0, e)
                    }) : g
                }
            },
            pseudos: {
                not: v(function(a, b, c) {
                    var d = j(a.replace(H, "$1"), b, c);
                    return function(a) {
                        return !d(a)
                    }
                }),
                enabled: function(a) {
                    return a.disabled === !1
                },
                disabled: function(a) {
                    return a.disabled === !0
                },
                checked: function(a) {
                    var b = a.nodeName.toLowerCase();
                    return b === "input" && !! a.checked || b === "option" && !! a.selected
                },
                selected: function(a) {
                    return a.parentNode && a.parentNode.selectedIndex, a.selected === !0
                },
                parent: function(a) {
                    return !f.pseudos.empty(a)
                },
                empty: function(a) {
                    var b;
                    a = a.firstChild;
                    while (a) {
                        if (a.nodeName > "@" || (b = a.nodeType) === 3 || b === 4) {
                            return !1
                        }
                        a = a.nextSibling
                    }
                    return !0
                },
                contains: v(function(a) {
                    return function(b) {
                        return (b.textContent || b.innerText || g(b)).indexOf(a) > -1
                    }
                }),
                has: v(function(a) {
                    return function(b) {
                        return $(a, b).length > 0
                    }
                }),
                header: function(a) {
                    return P.test(a.nodeName)
                },
                text: function(a) {
                    var b, c;
                    return a.nodeName.toLowerCase() === "input" && (b = a.type) === "text" && ((c = a.getAttribute("type")) == null || c.toLowerCase() === b)
                },
                radio: _("radio"),
                checkbox: _("checkbox"),
                file: _("file"),
                password: _("password"),
                image: _("image"),
                submit: ba("submit"),
                reset: ba("reset"),
                button: function(a) {
                    var b = a.nodeName.toLowerCase();
                    return b === "input" && a.type === "button" || b === "button"
                },
                input: function(a) {
                    return Q.test(a.nodeName)
                },
                focus: function(a) {
                    var b = a.ownerDocument;
                    return a === b.activeElement && (!b.hasFocus || b.hasFocus()) && ( !! a.type || !! a.href)
                },
                active: function(a) {
                    return a === a.ownerDocument.activeElement
                }
            },
            setFilters: {
                first: function(a, b, c) {
                    return c ? a.slice(1) : [a[0]]
                },
                last: function(a, b, c) {
                    var d = a.pop();
                    return c ? a : [d]
                },
                even: function(a, b, c) {
                    var d = [],
                        e = c ? 1 : 0,
                        f = a.length;
                    for (; e < f; e = e + 2) {
                        d.push(a[e])
                    }
                    return d
                },
                odd: function(a, b, c) {
                    var d = [],
                        e = c ? 0 : 1,
                        f = a.length;
                    for (; e < f; e = e + 2) {
                        d.push(a[e])
                    }
                    return d
                },
                lt: function(a, b, c) {
                    return c ? a.slice(+b) : a.slice(0, +b)
                },
                gt: function(a, b, c) {
                    return c ? a.slice(0, +b + 1) : a.slice(+b + 1)
                },
                eq: function(a, b, c) {
                    var d = a.splice(+b, 1);
                    return c ? a : d
                }
            }
        }, k = r.compareDocumentPosition ? function(a, b) {
            return a === b ? (l = !0, 0) : (!a.compareDocumentPosition || !b.compareDocumentPosition ? a.compareDocumentPosition : a.compareDocumentPosition(b) & 4) ? -1 : 1
        } : function(a, b) {
            if (a === b) {
                return l = !0, 0
            }
            if (a.sourceIndex && b.sourceIndex) {
                return a.sourceIndex - b.sourceIndex
            }
            var c, d, e = [],
                f = [],
                g = a.parentNode,
                h = b.parentNode,
                i = g;
            if (g === h) {
                return bb(a, b)
            }
            if (!g) {
                return -1
            }
            if (!h) {
                return 1
            }
            while (i) {
                e.unshift(i), i = i.parentNode
            }
            i = h;
            while (i) {
                f.unshift(i), i = i.parentNode
            }
            c = e.length, d = f.length;
            for (var j = 0; j < c && j < d; j++) {
                if (e[j] !== f[j]) {
                    return bb(e[j], f[j])
                }
            }
            return j === c ? bb(a, f[j], -1) : bb(e[j], b, 1)
        }, [0, 0].sort(k), m = !l, $.uniqueSort = function(a) {
            var b, c = 1;
            l = m, a.sort(k);
            if (l) {
                for (; b = a[c]; c++) {
                    b === a[c - 1] && a.splice(c--, 1)
                }
            }
            return a
        }, $.error = function(a) {
            throw new Error("Syntax error, unrecognized expression: " + a)
        }, j = $.compile = function(a, b, c) {
            var d, e, f, g = z[o][a];
            if (g && g.context === b) {
                return g
            }
            d = bc(a, b, c);
            for (e = 0, f = d.length; e < f; e++) {
                d[e] = bf(d[e], b, c)
            }
            return g = z(a, bg(d)), g.context = b, g.runs = g.dirruns = 0, g
        }, q.querySelectorAll && function() {
            var a, b = bk,
                c = /'|\\/g,
                d = /\=[\x20\t\r\n\f]*([^'"\]]*)[\x20\t\r\n\f]*\]/g,
                e = [],
                f = [":active"],
                g = r.matchesSelector || r.mozMatchesSelector || r.webkitMatchesSelector || r.oMatchesSelector || r.msMatchesSelector;
            T(function(a) {
                a.innerHTML = "<select><option selected=''></option></select>", a.querySelectorAll("[selected]").length || e.push("\\[" + A + "*(?:checked|disabled|ismap|multiple|readonly|selected|value)"), a.querySelectorAll(":checked").length || e.push(":checked")
            }), T(function(a) {
                a.innerHTML = "<p test=''></p>", a.querySelectorAll("[test^='']").length && e.push("[*^$]=" + A + "*(?:\"\"|'')"), a.innerHTML = "<input type='hidden'/>", a.querySelectorAll(":enabled").length || e.push(":enabled", ":disabled")
            }), e = e.length && new RegExp(e.join("|")), bk = function(a, d, f, g, h) {
                if (!g && !h && (!e || !e.test(a))) {
                    if (d.nodeType === 9) {
                        try {
                            return u.apply(f, t.call(d.querySelectorAll(a), 0)), f
                        } catch (i) {}
                    } else {
                        if (d.nodeType === 1 && d.nodeName.toLowerCase() !== "object") {
                            var j, k, l, m = d.getAttribute("id"),
                                n = m || o,
                                p = N.test(a) && d.parentNode || d;
                            m ? n = n.replace(c, "\\$&") : d.setAttribute("id", n), j = bc(a, d, h), n = "[id='" + n + "']";
                            for (k = 0, l = j.length; k < l; k++) {
                                j[k] = n + j[k].selector
                            }
                            try {
                                return u.apply(f, t.call(p.querySelectorAll(j.join(",")), 0)), f
                            } catch (i) {} finally {
                                m || d.removeAttribute("id")
                            }
                        }
                    }
                }
                return b(a, d, f, g, h)
            }, g && (T(function(b) {
                a = g.call(b, "div");
                try {
                    g.call(b, "[test!='']:sizzle"), f.push(S.PSEUDO.source, S.POS.source, "!=")
                } catch (c) {}
            }), f = new RegExp(f.join("|")), $.matchesSelector = function(b, c) {
                c = c.replace(d, "='$1']");
                if (!h(b) && !f.test(c) && (!e || !e.test(c))) {
                    try {
                        var i = g.call(b, c);
                        if (i || a || b.document && b.document.nodeType !== 11) {
                            return i
                        }
                    } catch (j) {}
                }
                return $(c, null, null, [b]).length > 0
            })
        }(), f.setFilters.nth = f.setFilters.eq, f.filters = f.pseudos, $.attr = p.attr, p.find = $, p.expr = $.selectors, p.expr[":"] = p.expr.pseudos, p.unique = $.uniqueSort, p.text = $.getText, p.isXMLDoc = $.isXML, p.contains = $.contains
    }(a);
    var bc = /Until$/,
        bd = /^(?:parents|prev(?:Until|All))/,
        be = /^.[^:#\[\.,]*$/,
        bf = p.expr.match.needsContext,
        bg = {
            children: !0,
            contents: !0,
            next: !0,
            prev: !0
        };
    p.fn.extend({
        find: function(a) {
            var b, c, d, e, f, g, h = this;
            if (typeof a != "string") {
                return p(a).filter(function() {
                    for (b = 0, c = h.length; b < c; b++) {
                        if (p.contains(h[b], this)) {
                            return !0
                        }
                    }
                })
            }
            g = this.pushStack("", "find", a);
            for (b = 0, c = this.length; b < c; b++) {
                d = g.length, p.find(a, this[b], g);
                if (b > 0) {
                    for (e = d; e < g.length; e++) {
                        for (f = 0; f < d; f++) {
                            if (g[f] === g[e]) {
                                g.splice(e--, 1);
                                break
                            }
                        }
                    }
                }
            }
            return g
        },
        has: function(a) {
            var b, c = p(a, this),
                d = c.length;
            return this.filter(function() {
                for (b = 0; b < d; b++) {
                    if (p.contains(this, c[b])) {
                        return !0
                    }
                }
            })
        },
        not: function(a) {
            return this.pushStack(bj(this, a, !1), "not", a)
        },
        filter: function(a) {
            return this.pushStack(bj(this, a, !0), "filter", a)
        },
        is: function(a) {
            return !!a && (typeof a == "string" ? bf.test(a) ? p(a, this.context).index(this[0]) >= 0 : p.filter(a, this).length > 0 : this.filter(a).length > 0)
        },
        closest: function(a, b) {
            var c, d = 0,
                e = this.length,
                f = [],
                g = bf.test(a) || typeof a != "string" ? p(a, b || this.context) : 0;
            for (; d < e; d++) {
                c = this[d];
                while (c && c.ownerDocument && c !== b && c.nodeType !== 11) {
                    if (g ? g.index(c) > -1 : p.find.matchesSelector(c, a)) {
                        f.push(c);
                        break
                    }
                    c = c.parentNode
                }
            }
            return f = f.length > 1 ? p.unique(f) : f, this.pushStack(f, "closest", a)
        },
        index: function(a) {
            return a ? typeof a == "string" ? p.inArray(this[0], p(a)) : p.inArray(a.jquery ? a[0] : a, this) : this[0] && this[0].parentNode ? this.prevAll().length : -1
        },
        add: function(a, b) {
            var c = typeof a == "string" ? p(a, b) : p.makeArray(a && a.nodeType ? [a] : a),
                d = p.merge(this.get(), c);
            return this.pushStack(bh(c[0]) || bh(d[0]) ? d : p.unique(d))
        },
        addBack: function(a) {
            return this.add(a == null ? this.prevObject : this.prevObject.filter(a))
        }
    }), p.fn.andSelf = p.fn.addBack, p.each({
        parent: function(a) {
            var b = a.parentNode;
            return b && b.nodeType !== 11 ? b : null
        },
        parents: function(a) {
            return p.dir(a, "parentNode")
        },
        parentsUntil: function(a, b, c) {
            return p.dir(a, "parentNode", c)
        },
        next: function(a) {
            return bi(a, "nextSibling")
        },
        prev: function(a) {
            return bi(a, "previousSibling")
        },
        nextAll: function(a) {
            return p.dir(a, "nextSibling")
        },
        prevAll: function(a) {
            return p.dir(a, "previousSibling")
        },
        nextUntil: function(a, b, c) {
            return p.dir(a, "nextSibling", c)
        },
        prevUntil: function(a, b, c) {
            return p.dir(a, "previousSibling", c)
        },
        siblings: function(a) {
            return p.sibling((a.parentNode || {}).firstChild, a)
        },
        children: function(a) {
            return p.sibling(a.firstChild)
        },
        contents: function(a) {
            return p.nodeName(a, "iframe") ? a.contentDocument || a.contentWindow.document : p.merge([], a.childNodes)
        }
    }, function(a, b) {
        p.fn[a] = function(c, d) {
            var e = p.map(this, b, c);
            return bc.test(a) || (d = c), d && typeof d == "string" && (e = p.filter(d, e)), e = this.length > 1 && !bg[a] ? p.unique(e) : e, this.length > 1 && bd.test(a) && (e = e.reverse()), this.pushStack(e, a, k.call(arguments).join(","))
        }
    }), p.extend({
        filter: function(a, b, c) {
            return c && (a = ":not(" + a + ")"), b.length === 1 ? p.find.matchesSelector(b[0], a) ? [b[0]] : [] : p.find.matches(a, b)
        },
        dir: function(a, c, d) {
            var e = [],
                f = a[c];
            while (f && f.nodeType !== 9 && (d === b || f.nodeType !== 1 || !p(f).is(d))) {
                f.nodeType === 1 && e.push(f), f = f[c]
            }
            return e
        },
        sibling: function(a, b) {
            var c = [];
            for (; a; a = a.nextSibling) {
                a.nodeType === 1 && a !== b && c.push(a)
            }
            return c
        }
    });
    var bl = "abbr|article|aside|audio|bdi|canvas|data|datalist|details|figcaption|figure|footer|header|hgroup|mark|meter|nav|output|progress|section|summary|time|video",
        bm = / jQuery\d+="(?:null|\d+)"/g,
        bn = /^\s+/,
        bo = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi,
        bp = /<([\w:]+)/,
        bq = /<tbody/i,
        br = /<|&#?\w+;/,
        bs = /<(?:script|style|link)/i,
        bt = /<(?:script|object|embed|option|style)/i,
        bu = new RegExp("<(?:" + bl + ")[\\s/>]", "i"),
        bv = /^(?:checkbox|radio)$/,
        bw = /checked\s*(?:[^=]|=\s*.checked.)/i,
        bx = /\/(java|ecma)script/i,
        by = /^\s*<!(?:\[CDATA\[|\-\-)|[\]\-]{2}>\s*$/g,
        bz = {
            option: [1, "<select multiple='multiple'>", "</select>"],
            legend: [1, "<fieldset>", "</fieldset>"],
            thead: [1, "<table>", "</table>"],
            tr: [2, "<table><tbody>", "</tbody></table>"],
            td: [3, "<table><tbody><tr>", "</tr></tbody></table>"],
            col: [2, "<table><tbody></tbody><colgroup>", "</colgroup></table>"],
            area: [1, "<map>", "</map>"],
            _default: [0, "", ""]
        }, bA = bk(e),
        bB = bA.appendChild(e.createElement("div"));
    bz.optgroup = bz.option, bz.tbody = bz.tfoot = bz.colgroup = bz.caption = bz.thead, bz.th = bz.td, p.support.htmlSerialize || (bz._default = [1, "X<div>", "</div>"]), p.fn.extend({
        text: function(a) {
            return p.access(this, function(a) {
                return a === b ? p.text(this) : this.empty().append((this[0] && this[0].ownerDocument || e).createTextNode(a))
            }, null, a, arguments.length)
        },
        wrapAll: function(a) {
            if (p.isFunction(a)) {
                return this.each(function(b) {
                    p(this).wrapAll(a.call(this, b))
                })
            }
            if (this[0]) {
                var b = p(a, this[0].ownerDocument).eq(0).clone(!0);
                this[0].parentNode && b.insertBefore(this[0]), b.map(function() {
                    var a = this;
                    while (a.firstChild && a.firstChild.nodeType === 1) {
                        a = a.firstChild
                    }
                    return a
                }).append(this)
            }
            return this
        },
        wrapInner: function(a) {
            return p.isFunction(a) ? this.each(function(b) {
                p(this).wrapInner(a.call(this, b))
            }) : this.each(function() {
                var b = p(this),
                    c = b.contents();
                c.length ? c.wrapAll(a) : b.append(a)
            })
        },
        wrap: function(a) {
            var b = p.isFunction(a);
            return this.each(function(c) {
                p(this).wrapAll(b ? a.call(this, c) : a)
            })
        },
        unwrap: function() {
            return this.parent().each(function() {
                p.nodeName(this, "body") || p(this).replaceWith(this.childNodes)
            }).end()
        },
        append: function() {
            return this.domManip(arguments, !0, function(a) {
                (this.nodeType === 1 || this.nodeType === 11) && this.appendChild(a)
            })
        },
        prepend: function() {
            return this.domManip(arguments, !0, function(a) {
                (this.nodeType === 1 || this.nodeType === 11) && this.insertBefore(a, this.firstChild)
            })
        },
        before: function() {
            if (!bh(this[0])) {
                return this.domManip(arguments, !1, function(a) {
                    this.parentNode.insertBefore(a, this)
                })
            }
            if (arguments.length) {
                var a = p.clean(arguments);
                return this.pushStack(p.merge(a, this), "before", this.selector)
            }
        },
        after: function() {
            if (!bh(this[0])) {
                return this.domManip(arguments, !1, function(a) {
                    this.parentNode.insertBefore(a, this.nextSibling)
                })
            }
            if (arguments.length) {
                var a = p.clean(arguments);
                return this.pushStack(p.merge(this, a), "after", this.selector)
            }
        },
        remove: function(a, b) {
            var c, d = 0;
            for (;
                (c = this[d]) != null; d++) {
                if (!a || p.filter(a, [c]).length) {
                    !b && c.nodeType === 1 && (p.cleanData(c.getElementsByTagName("*")), p.cleanData([c])), c.parentNode && c.parentNode.removeChild(c)
                }
            }
            return this
        },
        empty: function() {
            var a, b = 0;
            for (;
                (a = this[b]) != null; b++) {
                a.nodeType === 1 && p.cleanData(a.getElementsByTagName("*"));
                while (a.firstChild) {
                    a.removeChild(a.firstChild)
                }
            }
            return this
        },
        clone: function(a, b) {
            return a = a == null ? !1 : a, b = b == null ? a : b, this.map(function() {
                return p.clone(this, a, b)
            })
        },
        html: function(a) {
            return p.access(this, function(a) {
                var c = this[0] || {}, d = 0,
                    e = this.length;
                if (a === b) {
                    return c.nodeType === 1 ? c.innerHTML.replace(bm, "") : b
                }
                if (typeof a == "string" && !bs.test(a) && (p.support.htmlSerialize || !bu.test(a)) && (p.support.leadingWhitespace || !bn.test(a)) && !bz[(bp.exec(a) || ["", ""])[1].toLowerCase()]) {
                    a = a.replace(bo, "<$1></$2>");
                    try {
                        for (; d < e; d++) {
                            c = this[d] || {}, c.nodeType === 1 && (p.cleanData(c.getElementsByTagName("*")), c.innerHTML = a)
                        }
                        c = 0
                    } catch (f) {}
                }
                c && this.empty().append(a)
            }, null, a, arguments.length)
        },
        replaceWith: function(a) {
            return bh(this[0]) ? this.length ? this.pushStack(p(p.isFunction(a) ? a() : a), "replaceWith", a) : this : p.isFunction(a) ? this.each(function(b) {
                var c = p(this),
                    d = c.html();
                c.replaceWith(a.call(this, b, d))
            }) : (typeof a != "string" && (a = p(a).detach()), this.each(function() {
                var b = this.nextSibling,
                    c = this.parentNode;
                p(this).remove(), b ? p(b).before(a) : p(c).append(a)
            }))
        },
        detach: function(a) {
            return this.remove(a, !0)
        },
        domManip: function(a, c, d) {
            a = [].concat.apply([], a);
            var e, f, g, h, i = 0,
                j = a[0],
                k = [],
                l = this.length;
            if (!p.support.checkClone && l > 1 && typeof j == "string" && bw.test(j)) {
                return this.each(function() {
                    p(this).domManip(a, c, d)
                })
            }
            if (p.isFunction(j)) {
                return this.each(function(e) {
                    var f = p(this);
                    a[0] = j.call(this, e, c ? f.html() : b), f.domManip(a, c, d)
                })
            }
            if (this[0]) {
                e = p.buildFragment(a, this, k), g = e.fragment, f = g.firstChild, g.childNodes.length === 1 && (g = f);
                if (f) {
                    c = c && p.nodeName(f, "tr");
                    for (h = e.cacheable || l - 1; i < l; i++) {
                        d.call(c && p.nodeName(this[i], "table") ? bC(this[i], "tbody") : this[i], i === h ? g : p.clone(g, !0, !0))
                    }
                }
                g = f = null, k.length && p.each(k, function(a, b) {
                    b.src ? p.ajax ? p.ajax({
                        url: b.src,
                        type: "GET",
                        dataType: "script",
                        async: !1,
                        global: !1,
                        "throws": !0
                    }) : p.error("no ajax") : p.globalEval((b.text || b.textContent || b.innerHTML || "").replace(by, "")), b.parentNode && b.parentNode.removeChild(b)
                })
            }
            return this
        }
    }), p.buildFragment = function(a, c, d) {
        var f, g, h, i = a[0];
        return c = c || e, c = !c.nodeType && c[0] || c, c = c.ownerDocument || c, a.length === 1 && typeof i == "string" && i.length < 512 && c === e && i.charAt(0) === "<" && !bt.test(i) && (p.support.checkClone || !bw.test(i)) && (p.support.html5Clone || !bu.test(i)) && (g = !0, f = p.fragments[i], h = f !== b), f || (f = c.createDocumentFragment(), p.clean(a, c, f, d), g && (p.fragments[i] = h && f)), {
            fragment: f,
            cacheable: g
        }
    }, p.fragments = {}, p.each({
        appendTo: "append",
        prependTo: "prepend",
        insertBefore: "before",
        insertAfter: "after",
        replaceAll: "replaceWith"
    }, function(a, b) {
        p.fn[a] = function(c) {
            var d, e = 0,
                f = [],
                g = p(c),
                h = g.length,
                i = this.length === 1 && this[0].parentNode;
            if ((i == null || i && i.nodeType === 11 && i.childNodes.length === 1) && h === 1) {
                return g[b](this[0]), this
            }
            for (; e < h; e++) {
                d = (e > 0 ? this.clone(!0) : this).get(), p(g[e])[b](d), f = f.concat(d)
            }
            return this.pushStack(f, a, g.selector)
        }
    }), p.extend({
        clone: function(a, b, c) {
            var d, e, f, g;
            p.support.html5Clone || p.isXMLDoc(a) || !bu.test("<" + a.nodeName + ">") ? g = a.cloneNode(!0) : (bB.innerHTML = a.outerHTML, bB.removeChild(g = bB.firstChild));
            if ((!p.support.noCloneEvent || !p.support.noCloneChecked) && (a.nodeType === 1 || a.nodeType === 11) && !p.isXMLDoc(a)) {
                bE(a, g), d = bF(a), e = bF(g);
                for (f = 0; d[f]; ++f) {
                    e[f] && bE(d[f], e[f])
                }
            }
            if (b) {
                bD(a, g);
                if (c) {
                    d = bF(a), e = bF(g);
                    for (f = 0; d[f]; ++f) {
                        bD(d[f], e[f])
                    }
                }
            }
            return d = e = null, g
        },
        clean: function(a, b, c, d) {
            var f, g, h, i, j, k, l, m, n, o, q, r, s = b === e && bA,
                t = [];
            if (!b || typeof b.createDocumentFragment == "undefined") {
                b = e
            }
            for (f = 0;
                (h = a[f]) != null; f++) {
                typeof h == "number" && (h += "");
                if (!h) {
                    continue
                }
                if (typeof h == "string") {
                    if (!br.test(h)) {
                        h = b.createTextNode(h)
                    } else {
                        s = s || bk(b), l = b.createElement("div"), s.appendChild(l), h = h.replace(bo, "<$1></$2>"), i = (bp.exec(h) || ["", ""])[1].toLowerCase(), j = bz[i] || bz._default, k = j[0], l.innerHTML = j[1] + h + j[2];
                        while (k--) {
                            l = l.lastChild
                        }
                        if (!p.support.tbody) {
                            m = bq.test(h), n = i === "table" && !m ? l.firstChild && l.firstChild.childNodes : j[1] === "<table>" && !m ? l.childNodes : [];
                            for (g = n.length - 1; g >= 0; --g) {
                                p.nodeName(n[g], "tbody") && !n[g].childNodes.length && n[g].parentNode.removeChild(n[g])
                            }
                        }!p.support.leadingWhitespace && bn.test(h) && l.insertBefore(b.createTextNode(bn.exec(h)[0]), l.firstChild), h = l.childNodes, l.parentNode.removeChild(l)
                    }
                }
                h.nodeType ? t.push(h) : p.merge(t, h)
            }
            l && (h = l = s = null);
            if (!p.support.appendChecked) {
                for (f = 0;
                    (h = t[f]) != null; f++) {
                    p.nodeName(h, "input") ? bG(h) : typeof h.getElementsByTagName != "undefined" && p.grep(h.getElementsByTagName("input"), bG)
                }
            }
            if (c) {
                q = function(a) {
                    if (!a.type || bx.test(a.type)) {
                        return d ? d.push(a.parentNode ? a.parentNode.removeChild(a) : a) : c.appendChild(a)
                    }
                };
                for (f = 0;
                    (h = t[f]) != null; f++) {
                    if (!p.nodeName(h, "script") || !q(h)) {
                        c.appendChild(h), typeof h.getElementsByTagName != "undefined" && (r = p.grep(p.merge([], h.getElementsByTagName("script")), q), t.splice.apply(t, [f + 1, 0].concat(r)), f += r.length)
                    }
                }
            }
            return t
        },
        cleanData: function(a, b) {
            var c, d, e, f, g = 0,
                h = p.expando,
                i = p.cache,
                j = p.support.deleteExpando,
                k = p.event.special;
            for (;
                (e = a[g]) != null; g++) {
                if (b || p.acceptData(e)) {
                    d = e[h], c = d && i[d];
                    if (c) {
                        if (c.events) {
                            for (f in c.events) {
                                k[f] ? p.event.remove(e, f) : p.removeEvent(e, f, c.handle)
                            }
                        }
                        i[d] && (delete i[d], j ? delete e[h] : e.removeAttribute ? e.removeAttribute(h) : e[h] = null, p.deletedIds.push(d))
                    }
                }
            }
        }
    }),
    function() {
        var a, b;
        p.uaMatch = function(a) {
            a = a.toLowerCase();
            var b = /(chrome)[ \/]([\w.]+)/.exec(a) || /(webkit)[ \/]([\w.]+)/.exec(a) || /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(a) || /(msie) ([\w.]+)/.exec(a) || a.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(a) || [];
            return {
                browser: b[1] || "",
                version: b[2] || "0"
            }
        }, a = p.uaMatch(g.userAgent), b = {}, a.browser && (b[a.browser] = !0, b.version = a.version), b.chrome ? b.webkit = !0 : b.webkit && (b.safari = !0), p.browser = b, p.sub = function() {
            function a(b, c) {
                return new a.fn.init(b, c)
            }
            p.extend(!0, a, this), a.superclass = this, a.fn = a.prototype = this(), a.fn.constructor = a, a.sub = this.sub, a.fn.init = function c(c, d) {
                return d && d instanceof p && !(d instanceof a) && (d = a(d)), p.fn.init.call(this, c, d, b)
            }, a.fn.init.prototype = a.fn;
            var b = a(e);
            return a
        }
    }();
    var bH, bI, bJ, bK = /alpha\([^)]*\)/i,
        bL = /opacity=([^)]*)/,
        bM = /^(top|right|bottom|left)$/,
        bN = /^(none|table(?!-c[ea]).+)/,
        bO = /^margin/,
        bP = new RegExp("^(" + q + ")(.*)$", "i"),
        bQ = new RegExp("^(" + q + ")(?!px)[a-z%]+$", "i"),
        bR = new RegExp("^([-+])=(" + q + ")", "i"),
        bS = {}, bT = {
            position: "absolute",
            visibility: "hidden",
            display: "block"
        }, bU = {
            letterSpacing: 0,
            fontWeight: 400
        }, bV = ["Top", "Right", "Bottom", "Left"],
        bW = ["Webkit", "O", "Moz", "ms"],
        bX = p.fn.toggle;
    p.fn.extend({
        css: function(a, c) {
            return p.access(this, function(a, c, d) {
                return d !== b ? p.style(a, c, d) : p.css(a, c)
            }, a, c, arguments.length > 1)
        },
        show: function() {
            return b$(this, !0)
        },
        hide: function() {
            return b$(this)
        },
        toggle: function(a, b) {
            var c = typeof a == "boolean";
            return p.isFunction(a) && p.isFunction(b) ? bX.apply(this, arguments) : this.each(function() {
                (c ? a : bZ(this)) ? p(this).show() : p(this).hide()
            })
        }
    }), p.extend({
        cssHooks: {
            opacity: {
                get: function(a, b) {
                    if (b) {
                        var c = bH(a, "opacity");
                        return c === "" ? "1" : c
                    }
                }
            }
        },
        cssNumber: {
            fillOpacity: !0,
            fontWeight: !0,
            lineHeight: !0,
            opacity: !0,
            orphans: !0,
            widows: !0,
            zIndex: !0,
            zoom: !0
        },
        cssProps: {
            "float": p.support.cssFloat ? "cssFloat" : "styleFloat"
        },
        style: function(a, c, d, e) {
            if (!a || a.nodeType === 3 || a.nodeType === 8 || !a.style) {
                return
            }
            var f, g, h, i = p.camelCase(c),
                j = a.style;
            c = p.cssProps[i] || (p.cssProps[i] = bY(j, i)), h = p.cssHooks[c] || p.cssHooks[i];
            if (d === b) {
                return h && "get" in h && (f = h.get(a, !1, e)) !== b ? f : j[c]
            }
            g = typeof d, g === "string" && (f = bR.exec(d)) && (d = (f[1] + 1) * f[2] + parseFloat(p.css(a, c)), g = "number");
            if (d == null || g === "number" && isNaN(d)) {
                return
            }
            g === "number" && !p.cssNumber[i] && (d += "px");
            if (!h || !("set" in h) || (d = h.set(a, d, e)) !== b) {
                try {
                    j[c] = d
                } catch (k) {}
            }
        },
        css: function(a, c, d, e) {
            var f, g, h, i = p.camelCase(c);
            return c = p.cssProps[i] || (p.cssProps[i] = bY(a.style, i)), h = p.cssHooks[c] || p.cssHooks[i], h && "get" in h && (f = h.get(a, !0, e)), f === b && (f = bH(a, c)), f === "normal" && c in bU && (f = bU[c]), d || e !== b ? (g = parseFloat(f), d || p.isNumeric(g) ? g || 0 : f) : f
        },
        swap: function(a, b, c) {
            var d, e, f = {};
            for (e in b) {
                f[e] = a.style[e], a.style[e] = b[e]
            }
            d = c.call(a);
            for (e in b) {
                a.style[e] = f[e]
            }
            return d
        }
    }), a.getComputedStyle ? bH = function(b, c) {
        var d, e, f, g, h = a.getComputedStyle(b, null),
            i = b.style;
        return h && (d = h[c], d === "" && !p.contains(b.ownerDocument, b) && (d = p.style(b, c)), bQ.test(d) && bO.test(c) && (e = i.width, f = i.minWidth, g = i.maxWidth, i.minWidth = i.maxWidth = i.width = d, d = h.width, i.width = e, i.minWidth = f, i.maxWidth = g)), d
    } : e.documentElement.currentStyle && (bH = function(a, b) {
        var c, d, e = a.currentStyle && a.currentStyle[b],
            f = a.style;
        return e == null && f && f[b] && (e = f[b]), bQ.test(e) && !bM.test(b) && (c = f.left, d = a.runtimeStyle && a.runtimeStyle.left, d && (a.runtimeStyle.left = a.currentStyle.left), f.left = b === "fontSize" ? "1em" : e, e = f.pixelLeft + "px", f.left = c, d && (a.runtimeStyle.left = d)), e === "" ? "auto" : e
    }), p.each(["height", "width"], function(a, b) {
        p.cssHooks[b] = {
            get: function(a, c, d) {
                if (c) {
                    return a.offsetWidth === 0 && bN.test(bH(a, "display")) ? p.swap(a, bT, function() {
                        return cb(a, b, d)
                    }) : cb(a, b, d)
                }
            },
            set: function(a, c, d) {
                return b_(a, c, d ? ca(a, b, d, p.support.boxSizing && p.css(a, "boxSizing") === "border-box") : 0)
            }
        }
    }), p.support.opacity || (p.cssHooks.opacity = {
        get: function(a, b) {
            return bL.test((b && a.currentStyle ? a.currentStyle.filter : a.style.filter) || "") ? 0.01 * parseFloat(RegExp.$1) + "" : b ? "1" : ""
        },
        set: function(a, b) {
            var c = a.style,
                d = a.currentStyle,
                e = p.isNumeric(b) ? "alpha(opacity=" + b * 100 + ")" : "",
                f = d && d.filter || c.filter || "";
            c.zoom = 1;
            if (b >= 1 && p.trim(f.replace(bK, "")) === "" && c.removeAttribute) {
                c.removeAttribute("filter");
                if (d && !d.filter) {
                    return
                }
            }
            c.filter = bK.test(f) ? f.replace(bK, e) : f + " " + e
        }
    }), p(function() {
        p.support.reliableMarginRight || (p.cssHooks.marginRight = {
            get: function(a, b) {
                return p.swap(a, {
                    display: "inline-block"
                }, function() {
                    if (b) {
                        return bH(a, "marginRight")
                    }
                })
            }
        }), !p.support.pixelPosition && p.fn.position && p.each(["top", "left"], function(a, b) {
            p.cssHooks[b] = {
                get: function(a, c) {
                    if (c) {
                        var d = bH(a, b);
                        return bQ.test(d) ? p(a).position()[b] + "px" : d
                    }
                }
            }
        })
    }), p.expr && p.expr.filters && (p.expr.filters.hidden = function(a) {
        return a.offsetWidth === 0 && a.offsetHeight === 0 || !p.support.reliableHiddenOffsets && (a.style && a.style.display || bH(a, "display")) === "none"
    }, p.expr.filters.visible = function(a) {
        return !p.expr.filters.hidden(a)
    }), p.each({
        margin: "",
        padding: "",
        border: "Width"
    }, function(a, b) {
        p.cssHooks[a + b] = {
            expand: function(c) {
                var d, e = typeof c == "string" ? c.split(" ") : [c],
                    f = {};
                for (d = 0; d < 4; d++) {
                    f[a + bV[d] + b] = e[d] || e[d - 2] || e[0]
                }
                return f
            }
        }, bO.test(a) || (p.cssHooks[a + b].set = b_)
    });
    var cd = /%20/g,
        ce = /\[\]$/,
        cf = /\r?\n/g,
        cg = /^(?:color|date|datetime|datetime-local|email|hidden|month|number|password|range|search|tel|text|time|url|week)$/i,
        ch = /^(?:select|textarea)/i;
    p.fn.extend({
        serialize: function() {
            return p.param(this.serializeArray())
        },
        serializeArray: function() {
            return this.map(function() {
                return this.elements ? p.makeArray(this.elements) : this
            }).filter(function() {
                return this.name && !this.disabled && (this.checked || ch.test(this.nodeName) || cg.test(this.type))
            }).map(function(a, b) {
                var c = p(this).val();
                return c == null ? null : p.isArray(c) ? p.map(c, function(a, c) {
                    return {
                        name: b.name,
                        value: a.replace(cf, "\r\n")
                    }
                }) : {
                    name: b.name,
                    value: c.replace(cf, "\r\n")
                }
            }).get()
        }
    }), p.param = function(a, c) {
        var d, e = [],
            f = function(a, b) {
                b = p.isFunction(b) ? b() : b == null ? "" : b, e[e.length] = encodeURIComponent(a) + "=" + encodeURIComponent(b)
            };
        c === b && (c = p.ajaxSettings && p.ajaxSettings.traditional);
        if (p.isArray(a) || a.jquery && !p.isPlainObject(a)) {
            p.each(a, function() {
                f(this.name, this.value)
            })
        } else {
            for (d in a) {
                ci(d, a[d], c, f)
            }
        }
        return e.join("&").replace(cd, "+")
    };
    var cj, ck, cl = /#.*$/,
        cm = /^(.*?):[ \t]*([^\r\n]*)\r?$/mg,
        cn = /^(?:about|app|app\-storage|.+\-extension|file|res|widget):$/,
        co = /^(?:GET|HEAD)$/,
        cp = /^\/\//,
        cq = /\?/,
        cr = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi,
        cs = /([?&])_=[^&]*/,
        ct = /^([\w\+\.\-]+:)(?:\/\/([^\/?#:]*)(?::(\d+)|)|)/,
        cu = p.fn.load,
        cv = {}, cw = {}, cx = ["*/"] + ["*"];
    try {
        cj = f.href
    } catch (cy) {
        cj = e.createElement("a"), cj.href = "", cj = cj.href
    }
    ck = ct.exec(cj.toLowerCase()) || [], p.fn.load = function(a, c, d) {
        if (typeof a != "string" && cu) {
            return cu.apply(this, arguments)
        }
        if (!this.length) {
            return this
        }
        var e, f, g, h = this,
            i = a.indexOf(" ");
        return i >= 0 && (e = a.slice(i, a.length), a = a.slice(0, i)), p.isFunction(c) ? (d = c, c = b) : c && typeof c == "object" && (f = "POST"), p.ajax({
            url: a,
            type: f,
            dataType: "html",
            data: c,
            complete: function(a, b) {
                d && h.each(d, g || [a.responseText, b, a])
            }
        }).done(function(a) {
            g = arguments, h.html(e ? p("<div>").append(a.replace(cr, "")).find(e) : a)
        }), this
    }, p.each("ajaxStart ajaxStop ajaxComplete ajaxError ajaxSuccess ajaxSend".split(" "), function(a, b) {
        p.fn[b] = function(a) {
            return this.on(b, a)
        }
    }), p.each(["get", "post"], function(a, c) {
        p[c] = function(a, d, e, f) {
            return p.isFunction(d) && (f = f || e, e = d, d = b), p.ajax({
                type: c,
                url: a,
                data: d,
                success: e,
                dataType: f
            })
        }
    }), p.extend({
        getScript: function(a, c) {
            return p.get(a, b, c, "script")
        },
        getJSON: function(a, b, c) {
            return p.get(a, b, c, "json")
        },
        ajaxSetup: function(a, b) {
            return b ? cB(a, p.ajaxSettings) : (b = a, a = p.ajaxSettings), cB(a, b), a
        },
        ajaxSettings: {
            url: cj,
            isLocal: cn.test(ck[1]),
            global: !0,
            type: "GET",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            processData: !0,
            async: !0,
            accepts: {
                xml: "application/xml, text/xml",
                html: "text/html",
                text: "text/plain",
                json: "application/json, text/javascript",
                "*": cx
            },
            contents: {
                xml: /xml/,
                html: /html/,
                json: /json/
            },
            responseFields: {
                xml: "responseXML",
                text: "responseText"
            },
            converters: {
                "* text": a.String,
                "text html": !0,
                "text json": p.parseJSON,
                "text xml": p.parseXML
            },
            flatOptions: {
                context: !0,
                url: !0
            }
        },
        ajaxPrefilter: cz(cv),
        ajaxTransport: cz(cw),
        ajax: function(a, c) {
            function y(a, c, f, i) {
                var k, s, t, u, w, y = c;
                if (v === 2) {
                    return
                }
                v = 2, h && clearTimeout(h), g = b, e = i || "", x.readyState = a > 0 ? 4 : 0, f && (u = cC(l, x, f));
                if (a >= 200 && a < 300 || a === 304) {
                    l.ifModified && (w = x.getResponseHeader("Last-Modified"), w && (p.lastModified[d] = w), w = x.getResponseHeader("Etag"), w && (p.etag[d] = w)), a === 304 ? (y = "notmodified", k = !0) : (k = cD(l, u), y = k.state, s = k.data, t = k.error, k = !t)
                } else {
                    t = y;
                    if (!y || a) {
                        y = "error", a < 0 && (a = 0)
                    }
                }
                x.status = a, x.statusText = "" + (c || y), k ? o.resolveWith(m, [s, y, x]) : o.rejectWith(m, [x, y, t]), x.statusCode(r), r = b, j && n.trigger("ajax" + (k ? "Success" : "Error"), [x, l, k ? s : t]), q.fireWith(m, [x, y]), j && (n.trigger("ajaxComplete", [x, l]), --p.active || p.event.trigger("ajaxStop"))
            }
            typeof a == "object" && (c = a, a = b), c = c || {};
            var d, e, f, g, h, i, j, k, l = p.ajaxSetup({}, c),
                m = l.context || l,
                n = m !== l && (m.nodeType || m instanceof p) ? p(m) : p.event,
                o = p.Deferred(),
                q = p.Callbacks("once memory"),
                r = l.statusCode || {}, t = {}, u = {}, v = 0,
                w = "canceled",
                x = {
                    readyState: 0,
                    setRequestHeader: function(a, b) {
                        if (!v) {
                            var c = a.toLowerCase();
                            a = u[c] = u[c] || a, t[a] = b
                        }
                        return this
                    },
                    getAllResponseHeaders: function() {
                        return v === 2 ? e : null
                    },
                    getResponseHeader: function(a) {
                        var c;
                        if (v === 2) {
                            if (!f) {
                                f = {};
                                while (c = cm.exec(e)) {
                                    f[c[1].toLowerCase()] = c[2]
                                }
                            }
                            c = f[a.toLowerCase()]
                        }
                        return c === b ? null : c
                    },
                    overrideMimeType: function(a) {
                        return v || (l.mimeType = a), this
                    },
                    abort: function(a) {
                        return a = a || w, g && g.abort(a), y(0, a), this
                    }
                };
            o.promise(x), x.success = x.done, x.error = x.fail, x.complete = q.add, x.statusCode = function(a) {
                if (a) {
                    var b;
                    if (v < 2) {
                        for (b in a) {
                            r[b] = [r[b], a[b]]
                        }
                    } else {
                        b = a[x.status], x.always(b)
                    }
                }
                return this
            }, l.url = ((a || l.url) + "").replace(cl, "").replace(cp, ck[1] + "//"), l.dataTypes = p.trim(l.dataType || "*").toLowerCase().split(s), l.crossDomain == null && (i = ct.exec(l.url.toLowerCase()), l.crossDomain = !(!i || i[1] == ck[1] && i[2] == ck[2] && (i[3] || (i[1] === "http:" ? 80 : 443)) == (ck[3] || (ck[1] === "http:" ? 80 : 443)))), l.data && l.processData && typeof l.data != "string" && (l.data = p.param(l.data, l.traditional)), cA(cv, l, c, x);
            if (v === 2) {
                return x
            }
            j = l.global, l.type = l.type.toUpperCase(), l.hasContent = !co.test(l.type), j && p.active++ === 0 && p.event.trigger("ajaxStart");
            if (!l.hasContent) {
                l.data && (l.url += (cq.test(l.url) ? "&" : "?") + l.data, delete l.data), d = l.url;
                if (l.cache === !1) {
                    var z = p.now(),
                        A = l.url.replace(cs, "$1_=" + z);
                    l.url = A + (A === l.url ? (cq.test(l.url) ? "&" : "?") + "_=" + z : "")
                }
            }(l.data && l.hasContent && l.contentType !== !1 || c.contentType) && x.setRequestHeader("Content-Type", l.contentType), l.ifModified && (d = d || l.url, p.lastModified[d] && x.setRequestHeader("If-Modified-Since", p.lastModified[d]), p.etag[d] && x.setRequestHeader("If-None-Match", p.etag[d])), x.setRequestHeader("Accept", l.dataTypes[0] && l.accepts[l.dataTypes[0]] ? l.accepts[l.dataTypes[0]] + (l.dataTypes[0] !== "*" ? ", " + cx + "; q=0.01" : "") : l.accepts["*"]);
            for (k in l.headers) {
                x.setRequestHeader(k, l.headers[k])
            }
            if (!l.beforeSend || l.beforeSend.call(m, x, l) !== !1 && v !== 2) {
                w = "abort";
                for (k in {
                    success: 1,
                    error: 1,
                    complete: 1
                }) {
                    x[k](l[k])
                }
                g = cA(cw, l, c, x);
                if (!g) {
                    y(-1, "No Transport")
                } else {
                    x.readyState = 1, j && n.trigger("ajaxSend", [x, l]), l.async && l.timeout > 0 && (h = setTimeout(function() {
                        x.abort("timeout")
                    }, l.timeout));
                    try {
                        v = 1, g.send(t, y)
                    } catch (B) {
                        if (v < 2) {
                            y(-1, B)
                        } else {
                            throw B
                        }
                    }
                }
                return x
            }
            return x.abort()
        },
        active: 0,
        lastModified: {},
        etag: {}
    });
    var cE = [],
        cF = /\?/,
        cG = /(=)\?(?=&|$)|\?\?/,
        cH = p.now();
    p.ajaxSetup({
        jsonp: "callback",
        jsonpCallback: function() {
            var a = cE.pop() || p.expando + "_" + cH++;
            return this[a] = !0, a
        }
    }), p.ajaxPrefilter("json jsonp", function(c, d, e) {
        var f, g, h, i = c.data,
            j = c.url,
            k = c.jsonp !== !1,
            l = k && cG.test(j),
            m = k && !l && typeof i == "string" && !(c.contentType || "").indexOf("application/x-www-form-urlencoded") && cG.test(i);
        if (c.dataTypes[0] === "jsonp" || l || m) {
            return f = c.jsonpCallback = p.isFunction(c.jsonpCallback) ? c.jsonpCallback() : c.jsonpCallback, g = a[f], l ? c.url = j.replace(cG, "$1" + f) : m ? c.data = i.replace(cG, "$1" + f) : k && (c.url += (cF.test(j) ? "&" : "?") + c.jsonp + "=" + f), c.converters["script json"] = function() {
                return h || p.error(f + " was not called"), h[0]
            }, c.dataTypes[0] = "json", a[f] = function() {
                h = arguments
            }, e.always(function() {
                a[f] = g, c[f] && (c.jsonpCallback = d.jsonpCallback, cE.push(f)), h && p.isFunction(g) && g(h[0]), h = g = b
            }), "script"
        }
    }), p.ajaxSetup({
        accepts: {
            script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"
        },
        contents: {
            script: /javascript|ecmascript/
        },
        converters: {
            "text script": function(a) {
                return p.globalEval(a), a
            }
        }
    }), p.ajaxPrefilter("script", function(a) {
        a.cache === b && (a.cache = !1), a.crossDomain && (a.type = "GET", a.global = !1)
    }), p.ajaxTransport("script", function(a) {
        if (a.crossDomain) {
            var c, d = e.head || e.getElementsByTagName("head")[0] || e.documentElement;
            return {
                send: function(f, g) {
                    c = e.createElement("script"), c.async = "async", a.scriptCharset && (c.charset = a.scriptCharset), c.src = a.url, c.onload = c.onreadystatechange = function(a, e) {
                        if (e || !c.readyState || /loaded|complete/.test(c.readyState)) {
                            c.onload = c.onreadystatechange = null, d && c.parentNode && d.removeChild(c), c = b, e || g(200, "success")
                        }
                    }, d.insertBefore(c, d.firstChild)
                },
                abort: function() {
                    c && c.onload(0, 1)
                }
            }
        }
    });
    var cI, cJ = a.ActiveXObject ? function() {
            for (var a in cI) {
                cI[a](0, 1)
            }
        } : !1,
        cK = 0;
    p.ajaxSettings.xhr = a.ActiveXObject ? function() {
        return !this.isLocal && cL() || cM()
    } : cL,
    function(a) {
        p.extend(p.support, {
            ajax: !! a,
            cors: !! a && "withCredentials" in a
        })
    }(p.ajaxSettings.xhr()), p.support.ajax && p.ajaxTransport(function(c) {
        if (!c.crossDomain || p.support.cors) {
            var d;
            return {
                send: function(e, f) {
                    var g, h, i = c.xhr();
                    c.username ? i.open(c.type, c.url, c.async, c.username, c.password) : i.open(c.type, c.url, c.async);
                    if (c.xhrFields) {
                        for (h in c.xhrFields) {
                            i[h] = c.xhrFields[h]
                        }
                    }
                    c.mimeType && i.overrideMimeType && i.overrideMimeType(c.mimeType), !c.crossDomain && !e["X-Requested-With"] && (e["X-Requested-With"] = "XMLHttpRequest");
                    try {
                        for (h in e) {
                            i.setRequestHeader(h, e[h])
                        }
                    } catch (j) {}
                    i.send(c.hasContent && c.data || null), d = function(a, e) {
                        var h, j, k, l, m;
                        try {
                            if (d && (e || i.readyState === 4)) {
                                d = b, g && (i.onreadystatechange = p.noop, cJ && delete cI[g]);
                                if (e) {
                                    i.readyState !== 4 && i.abort()
                                } else {
                                    h = i.status, k = i.getAllResponseHeaders(), l = {}, m = i.responseXML, m && m.documentElement && (l.xml = m);
                                    try {
                                        l.text = i.responseText
                                    } catch (a) {}
                                    try {
                                        j = i.statusText
                                    } catch (n) {
                                        j = ""
                                    }!h && c.isLocal && !c.crossDomain ? h = l.text ? 200 : 404 : h === 1223 && (h = 204)
                                }
                            }
                        } catch (o) {
                            e || f(-1, o)
                        }
                        l && f(h, j, l, k)
                    }, c.async ? i.readyState === 4 ? setTimeout(d, 0) : (g = ++cK, cJ && (cI || (cI = {}, p(a).unload(cJ)), cI[g] = d), i.onreadystatechange = d) : d()
                },
                abort: function() {
                    d && d(0, 1)
                }
            }
        }
    });
    var cN, cO, cP = /^(?:toggle|show|hide)$/,
        cQ = new RegExp("^(?:([-+])=|)(" + q + ")([a-z%]*)$", "i"),
        cR = /queueHooks$/,
        cS = [cY],
        cT = {
            "*": [
                function(a, b) {
                    var c, d, e, f = this.createTween(a, b),
                        g = cQ.exec(b),
                        h = f.cur(),
                        i = +h || 0,
                        j = 1;
                    if (g) {
                        c = +g[2], d = g[3] || (p.cssNumber[a] ? "" : "px");
                        if (d !== "px" && i) {
                            i = p.css(f.elem, a, !0) || c || 1;
                            do {
                                e = j = j || ".5", i = i / j, p.style(f.elem, a, i + d), j = f.cur() / h
                            } while (j !== 1 && j !== e)
                        }
                        f.unit = d, f.start = i, f.end = g[1] ? i + (g[1] + 1) * c : c
                    }
                    return f
                }
            ]
        };
    p.Animation = p.extend(cW, {
        tweener: function(a, b) {
            p.isFunction(a) ? (b = a, a = ["*"]) : a = a.split(" ");
            var c, d = 0,
                e = a.length;
            for (; d < e; d++) {
                c = a[d], cT[c] = cT[c] || [], cT[c].unshift(b)
            }
        },
        prefilter: function(a, b) {
            b ? cS.unshift(a) : cS.push(a)
        }
    }), p.Tween = cZ, cZ.prototype = {
        constructor: cZ,
        init: function(a, b, c, d, e, f) {
            this.elem = a, this.prop = c, this.easing = e || "swing", this.options = b, this.start = this.now = this.cur(), this.end = d, this.unit = f || (p.cssNumber[c] ? "" : "px")
        },
        cur: function() {
            var a = cZ.propHooks[this.prop];
            return a && a.get ? a.get(this) : cZ.propHooks._default.get(this)
        },
        run: function(a) {
            var b, c = cZ.propHooks[this.prop];
            return this.options.duration ? this.pos = b = p.easing[this.easing](a, this.options.duration * a, 0, 1, this.options.duration) : this.pos = b = a, this.now = (this.end - this.start) * b + this.start, this.options.step && this.options.step.call(this.elem, this.now, this), c && c.set ? c.set(this) : cZ.propHooks._default.set(this), this
        }
    }, cZ.prototype.init.prototype = cZ.prototype, cZ.propHooks = {
        _default: {
            get: function(a) {
                var b;
                return a.elem[a.prop] == null || !! a.elem.style && a.elem.style[a.prop] != null ? (b = p.css(a.elem, a.prop, !1, ""), !b || b === "auto" ? 0 : b) : a.elem[a.prop]
            },
            set: function(a) {
                p.fx.step[a.prop] ? p.fx.step[a.prop](a) : a.elem.style && (a.elem.style[p.cssProps[a.prop]] != null || p.cssHooks[a.prop]) ? p.style(a.elem, a.prop, a.now + a.unit) : a.elem[a.prop] = a.now
            }
        }
    }, cZ.propHooks.scrollTop = cZ.propHooks.scrollLeft = {
        set: function(a) {
            a.elem.nodeType && a.elem.parentNode && (a.elem[a.prop] = a.now)
        }
    }, p.each(["toggle", "show", "hide"], function(a, b) {
        var c = p.fn[b];
        p.fn[b] = function(d, e, f) {
            return d == null || typeof d == "boolean" || !a && p.isFunction(d) && p.isFunction(e) ? c.apply(this, arguments) : this.animate(c$(b, !0), d, e, f)
        }
    }), p.fn.extend({
        fadeTo: function(a, b, c, d) {
            return this.filter(bZ).css("opacity", 0).show().end().animate({
                opacity: b
            }, a, c, d)
        },
        animate: function(a, b, c, d) {
            var e = p.isEmptyObject(a),
                f = p.speed(b, c, d),
                g = function() {
                    var b = cW(this, p.extend({}, a), f);
                    e && b.stop(!0)
                };
            return e || f.queue === !1 ? this.each(g) : this.queue(f.queue, g)
        },
        stop: function(a, c, d) {
            var e = function(a) {
                var b = a.stop;
                delete a.stop, b(d)
            };
            return typeof a != "string" && (d = c, c = a, a = b), c && a !== !1 && this.queue(a || "fx", []), this.each(function() {
                var b = !0,
                    c = a != null && a + "queueHooks",
                    f = p.timers,
                    g = p._data(this);
                if (c) {
                    g[c] && g[c].stop && e(g[c])
                } else {
                    for (c in g) {
                        g[c] && g[c].stop && cR.test(c) && e(g[c])
                    }
                }
                for (c = f.length; c--;) {
                    f[c].elem === this && (a == null || f[c].queue === a) && (f[c].anim.stop(d), b = !1, f.splice(c, 1))
                }(b || !d) && p.dequeue(this, a)
            })
        }
    }), p.each({
        slideDown: c$("show"),
        slideUp: c$("hide"),
        slideToggle: c$("toggle"),
        fadeIn: {
            opacity: "show"
        },
        fadeOut: {
            opacity: "hide"
        },
        fadeToggle: {
            opacity: "toggle"
        }
    }, function(a, b) {
        p.fn[a] = function(a, c, d) {
            return this.animate(b, a, c, d)
        }
    }), p.speed = function(a, b, c) {
        var d = a && typeof a == "object" ? p.extend({}, a) : {
            complete: c || !c && b || p.isFunction(a) && a,
            duration: a,
            easing: c && b || b && !p.isFunction(b) && b
        };
        d.duration = p.fx.off ? 0 : typeof d.duration == "number" ? d.duration : d.duration in p.fx.speeds ? p.fx.speeds[d.duration] : p.fx.speeds._default;
        if (d.queue == null || d.queue === !0) {
            d.queue = "fx"
        }
        return d.old = d.complete, d.complete = function() {
            p.isFunction(d.old) && d.old.call(this), d.queue && p.dequeue(this, d.queue)
        }, d
    }, p.easing = {
        linear: function(a) {
            return a
        },
        swing: function(a) {
            return 0.5 - Math.cos(a * Math.PI) / 2
        }
    }, p.timers = [], p.fx = cZ.prototype.init, p.fx.tick = function() {
        var a, b = p.timers,
            c = 0;
        for (; c < b.length; c++) {
            a = b[c], !a() && b[c] === a && b.splice(c--, 1)
        }
        b.length || p.fx.stop()
    }, p.fx.timer = function(a) {
        a() && p.timers.push(a) && !cO && (cO = setInterval(p.fx.tick, p.fx.interval))
    }, p.fx.interval = 13, p.fx.stop = function() {
        clearInterval(cO), cO = null
    }, p.fx.speeds = {
        slow: 600,
        fast: 200,
        _default: 400
    }, p.fx.step = {}, p.expr && p.expr.filters && (p.expr.filters.animated = function(a) {
        return p.grep(p.timers, function(b) {
            return a === b.elem
        }).length
    });
    var c_ = /^(?:body|html)$/i;
    p.fn.offset = function(a) {
        if (arguments.length) {
            return a === b ? this : this.each(function(b) {
                p.offset.setOffset(this, a, b)
            })
        }
        var c, d, e, f, g, h, i, j, k, l, m = this[0],
            n = m && m.ownerDocument;
        if (!n) {
            return
        }
        return (e = n.body) === m ? p.offset.bodyOffset(m) : (d = n.documentElement, p.contains(d, m) ? (c = m.getBoundingClientRect(), f = da(n), g = d.clientTop || e.clientTop || 0, h = d.clientLeft || e.clientLeft || 0, i = f.pageYOffset || d.scrollTop, j = f.pageXOffset || d.scrollLeft, k = c.top + i - g, l = c.left + j - h, {
            top: k,
            left: l
        }) : {
            top: 0,
            left: 0
        })
    }, p.offset = {
        bodyOffset: function(a) {
            var b = a.offsetTop,
                c = a.offsetLeft;
            return p.support.doesNotIncludeMarginInBodyOffset && (b += parseFloat(p.css(a, "marginTop")) || 0, c += parseFloat(p.css(a, "marginLeft")) || 0), {
                top: b,
                left: c
            }
        },
        setOffset: function(a, b, c) {
            var d = p.css(a, "position");
            d === "static" && (a.style.position = "relative");
            var e = p(a),
                f = e.offset(),
                g = p.css(a, "top"),
                h = p.css(a, "left"),
                i = (d === "absolute" || d === "fixed") && p.inArray("auto", [g, h]) > -1,
                j = {}, k = {}, l, m;
            i ? (k = e.position(), l = k.top, m = k.left) : (l = parseFloat(g) || 0, m = parseFloat(h) || 0), p.isFunction(b) && (b = b.call(a, c, f)), b.top != null && (j.top = b.top - f.top + l), b.left != null && (j.left = b.left - f.left + m), "using" in b ? b.using.call(a, j) : e.css(j)
        }
    }, p.fn.extend({
        position: function() {
            if (!this[0]) {
                return
            }
            var a = this[0],
                b = this.offsetParent(),
                c = this.offset(),
                d = c_.test(b[0].nodeName) ? {
                    top: 0,
                    left: 0
                } : b.offset();
            return c.top -= parseFloat(p.css(a, "marginTop")) || 0, c.left -= parseFloat(p.css(a, "marginLeft")) || 0, d.top += parseFloat(p.css(b[0], "borderTopWidth")) || 0, d.left += parseFloat(p.css(b[0], "borderLeftWidth")) || 0, {
                top: c.top - d.top,
                left: c.left - d.left
            }
        },
        offsetParent: function() {
            return this.map(function() {
                var a = this.offsetParent || e.body;
                while (a && !c_.test(a.nodeName) && p.css(a, "position") === "static") {
                    a = a.offsetParent
                }
                return a || e.body
            })
        }
    }), p.each({
        scrollLeft: "pageXOffset",
        scrollTop: "pageYOffset"
    }, function(a, c) {
        var d = /Y/.test(c);
        p.fn[a] = function(e) {
            return p.access(this, function(a, e, f) {
                var g = da(a);
                if (f === b) {
                    return g ? c in g ? g[c] : g.document.documentElement[e] : a[e]
                }
                g ? g.scrollTo(d ? p(g).scrollLeft() : f, d ? f : p(g).scrollTop()) : a[e] = f
            }, a, e, arguments.length, null)
        }
    }), p.each({
        Height: "height",
        Width: "width"
    }, function(a, c) {
        p.each({
            padding: "inner" + a,
            content: c,
            "": "outer" + a
        }, function(d, e) {
            p.fn[e] = function(e, f) {
                var g = arguments.length && (d || typeof e != "boolean"),
                    h = d || (e === !0 || f === !0 ? "margin" : "border");
                return p.access(this, function(c, d, e) {
                    var f;
                    return p.isWindow(c) ? c.document.documentElement["client" + a] : c.nodeType === 9 ? (f = c.documentElement, Math.max(c.body["scroll" + a], f["scroll" + a], c.body["offset" + a], f["offset" + a], f["client" + a])) : e === b ? p.css(c, d, e, h) : p.style(c, d, e, h)
                }, c, g ? e : b, g, null)
            }
        })
    }), a.jQuery = a.$ = p, typeof define == "function" && define.amd && define.amd.jQuery && define("jquery", [], function() {
        return p
    })
})(window);
var JSEncryptExports = {};
(function(bw) {
    var aP;
    var aw = 244837814094590;
    var af = ((aw & 16777215) == 15715070);

    function T(L, t, z) {
        if (L != null) {
            if ("number" == typeof L) {
                this.fromNumber(L, t, z)
            } else {
                if (t == null && "string" != typeof L) {
                    this.fromString(L, 256)
                } else {
                    this.fromString(L, t)
                }
            }
        }
    }

    function ao() {
        return new T(null)
    }

    function k(bV, L, bT, bW, t, bU) {
        while (--bU >= 0) {
            var z = L * this[bV++] + bT[bW] + t;
            t = Math.floor(z / 67108864);
            bT[bW++] = z & 67108863
        }
        return t
    }

    function B(bT, bY, bZ, z, bW, b0) {
        var bV = bY & 32767,
            bX = bY >> 15;
        while (--b0 >= 0) {
            var t = this[bT] & 32767;
            var bU = this[bT++] >> 15;
            var L = bX * t + bU * bV;
            t = bV * t + ((L & 32767) << 15) + bZ[z] + (bW & 1073741823);
            bW = (t >>> 30) + (L >>> 15) + bX * bU + (bW >>> 30);
            bZ[z++] = t & 1073741823
        }
        return bW
    }

    function ar(bT, bY, bZ, z, bW, b0) {
        var bV = bY & 16383,
            bX = bY >> 14;
        while (--b0 >= 0) {
            var t = this[bT] & 16383;
            var bU = this[bT++] >> 14;
            var L = bX * t + bU * bV;
            t = bV * t + ((L & 16383) << 14) + bZ[z] + bW;
            bW = (t >> 28) + (L >> 14) + bX * bU;
            bZ[z++] = t & 268435455
        }
        return bW
    }
    if (af && (navigator.appName == "Microsoft Internet Explorer")) {
        T.prototype.am = B;
        aP = 30
    } else {
        if (af && (navigator.appName != "Netscape")) {
            T.prototype.am = k;
            aP = 26
        } else {
            T.prototype.am = ar;
            aP = 28
        }
    }
    T.prototype.DB = aP;
    T.prototype.DM = ((1 << aP) - 1);
    T.prototype.DV = (1 << aP);
    var bc = 52;
    T.prototype.FV = Math.pow(2, bc);
    T.prototype.F1 = bc - aP;
    T.prototype.F2 = 2 * aP - bc;
    var bR = "0123456789abcdefghijklmnopqrstuvwxyz";
    var N = new Array();
    var q, a7;
    q = "0".charCodeAt(0);
    for (a7 = 0; a7 <= 9; ++a7) {
        N[q++] = a7
    }
    q = "a".charCodeAt(0);
    for (a7 = 10; a7 < 36; ++a7) {
        N[q++] = a7
    }
    q = "A".charCodeAt(0);
    for (a7 = 10; a7 < 36; ++a7) {
        N[q++] = a7
    }

    function aQ(t) {
        return bR.charAt(t)
    }

    function bp(L, t) {
        var z = N[L.charCodeAt(t)];
        return (z == null) ? -1 : z
    }

    function Y(t) {
        for (var z = this.t - 1; z >= 0; --z) {
            t[z] = this[z]
        }
        t.t = this.t;
        t.s = this.s
    }

    function f(t) {
        this.t = 1;
        this.s = (t < 0) ? -1 : 0;
        if (t > 0) {
            this[0] = t
        } else {
            if (t < -1) {
                this[0] = t + DV
            } else {
                this.t = 0
            }
        }
    }

    function aK(z) {
        var t = ao();
        t.fromInt(z);
        return t
    }

    function bL(bT, t) {
        var bW;
        if (t == 16) {
            bW = 4
        } else {
            if (t == 8) {
                bW = 3
            } else {
                if (t == 256) {
                    bW = 8
                } else {
                    if (t == 2) {
                        bW = 1
                    } else {
                        if (t == 32) {
                            bW = 5
                        } else {
                            if (t == 4) {
                                bW = 2
                            } else {
                                this.fromRadix(bT, t);
                                return
                            }
                        }
                    }
                }
            }
        }
        this.t = 0;
        this.s = 0;
        var bV = bT.length,
            bU = false,
            L = 0;
        while (--bV >= 0) {
            var z = (bW == 8) ? bT[bV] & 255 : bp(bT, bV);
            if (z < 0) {
                if (bT.charAt(bV) == "-") {
                    bU = true
                }
                continue
            }
            bU = false;
            if (L == 0) {
                this[this.t++] = z
            } else {
                if (L + bW > this.DB) {
                    this[this.t - 1] |= (z & ((1 << (this.DB - L)) - 1)) << L;
                    this[this.t++] = (z >> (this.DB - L))
                } else {
                    this[this.t - 1] |= z << L
                }
            }
            L += bW;
            if (L >= this.DB) {
                L -= this.DB
            }
        }
        if (bW == 8 && (bT[0] & 128) != 0) {
            this.s = -1;
            if (L > 0) {
                this[this.t - 1] |= ((1 << (this.DB - L)) - 1) << L
            }
        }
        this.clamp();
        if (bU) {
            T.ZERO.subTo(this, this)
        }
    }

    function ai() {
        var t = this.s & this.DM;
        while (this.t > 0 && this[this.t - 1] == t) {
            --this.t
        }
    }

    function a9(t) {
        if (this.s < 0) {
            return "-" + this.negate().toString(t)
        }
        var bV;
        if (t == 16) {
            bV = 4
        } else {
            if (t == 8) {
                bV = 3
            } else {
                if (t == 2) {
                    bV = 1
                } else {
                    if (t == 32) {
                        bV = 5
                    } else {
                        if (t == 4) {
                            bV = 2
                        } else {
                            return this.toRadix(t)
                        }
                    }
                }
            }
        }
        var L = (1 << bV) - 1,
            bT, bX = false,
            z = "",
            bW = this.t;
        var bU = this.DB - (bW * this.DB) % bV;
        if (bW-- > 0) {
            if (bU < this.DB && (bT = this[bW] >> bU) > 0) {
                bX = true;
                z = aQ(bT)
            }
            while (bW >= 0) {
                if (bU < bV) {
                    bT = (this[bW] & ((1 << bU) - 1)) << (bV - bU);
                    bT |= this[--bW] >> (bU += this.DB - bV)
                } else {
                    bT = (this[bW] >> (bU -= bV)) & L;
                    if (bU <= 0) {
                        bU += this.DB;
                        --bW
                    }
                } if (bT > 0) {
                    bX = true
                }
                if (bX) {
                    z += aQ(bT)
                }
            }
        }
        return bX ? z : "0"
    }

    function Q() {
        var t = ao();
        T.ZERO.subTo(this, t);
        return t
    }

    function aB() {
        return (this.s < 0) ? this.negate() : this
    }

    function ah(t) {
        var z = this.s - t.s;
        if (z != 0) {
            return z
        }
        var L = this.t;
        z = L - t.t;
        if (z != 0) {
            return (this.s < 0) ? -z : z
        }
        while (--L >= 0) {
            if ((z = this[L] - t[L]) != 0) {
                return z
            }
        }
        return 0
    }

    function H(L) {
        var t = 1,
            z;
        if ((z = L >>> 16) != 0) {
            L = z;
            t += 16
        }
        if ((z = L >> 8) != 0) {
            L = z;
            t += 8
        }
        if ((z = L >> 4) != 0) {
            L = z;
            t += 4
        }
        if ((z = L >> 2) != 0) {
            L = z;
            t += 2
        }
        if ((z = L >> 1) != 0) {
            L = z;
            t += 1
        }
        return t
    }

    function c() {
        if (this.t <= 0) {
            return 0
        }
        return this.DB * (this.t - 1) + H(this[this.t - 1] ^ (this.s & this.DM))
    }

    function d(z, L) {
        var t;
        for (t = this.t - 1; t >= 0; --t) {
            L[t + z] = this[t]
        }
        for (t = z - 1; t >= 0; --t) {
            L[t] = 0
        }
        L.t = this.t + z;
        L.s = this.s
    }

    function bi(z, L) {
        for (var t = z; t < this.t; ++t) {
            L[t - z] = this[t]
        }
        L.t = Math.max(this.t - z, 0);
        L.s = this.s
    }

    function ba(bT, bW) {
        var t = bT % this.DB;
        var L = this.DB - t;
        var bX = (1 << L) - 1;
        var bV = Math.floor(bT / this.DB),
            bU = (this.s << t) & this.DM,
            z;
        for (z = this.t - 1; z >= 0; --z) {
            bW[z + bV + 1] = (this[z] >> L) | bU;
            bU = (this[z] & bX) << t
        }
        for (z = bV - 1; z >= 0; --z) {
            bW[z] = 0
        }
        bW[bV] = bU;
        bW.t = this.t + bV + 1;
        bW.s = this.s;
        bW.clamp()
    }

    function aI(bT, bW) {
        bW.s = this.s;
        var bV = Math.floor(bT / this.DB);
        if (bV >= this.t) {
            bW.t = 0;
            return
        }
        var t = bT % this.DB;
        var L = this.DB - t;
        var bU = (1 << t) - 1;
        bW[0] = this[bV] >> t;
        for (var z = bV + 1; z < this.t; ++z) {
            bW[z - bV - 1] |= (this[z] & bU) << L;
            bW[z - bV] = this[z] >> t
        }
        if (t > 0) {
            bW[this.t - bV - 1] |= (this.s & bU) << L
        }
        bW.t = this.t - bV;
        bW.clamp()
    }

    function ak(t, bT) {
        var L = 0,
            bU = 0,
            z = Math.min(t.t, this.t);
        while (L < z) {
            bU += this[L] - t[L];
            bT[L++] = bU & this.DM;
            bU >>= this.DB
        }
        if (t.t < this.t) {
            bU -= t.s;
            while (L < this.t) {
                bU += this[L];
                bT[L++] = bU & this.DM;
                bU >>= this.DB
            }
            bU += this.s
        } else {
            bU += this.s;
            while (L < t.t) {
                bU -= t[L];
                bT[L++] = bU & this.DM;
                bU >>= this.DB
            }
            bU -= t.s
        }
        bT.s = (bU < 0) ? -1 : 0;
        if (bU < -1) {
            bT[L++] = this.DV + bU
        } else {
            if (bU > 0) {
                bT[L++] = bU
            }
        }
        bT.t = L;
        bT.clamp()
    }

    function bO(t, bT) {
        var bU = this.abs(),
            L = t.abs();
        var z = bU.t;
        bT.t = z + L.t;
        while (--z >= 0) {
            bT[z] = 0
        }
        for (z = 0; z < L.t; ++z) {
            bT[z + bU.t] = bU.am(0, L[z], bT, z, 0, bU.t)
        }
        bT.s = 0;
        bT.clamp();
        if (this.s != t.s) {
            T.ZERO.subTo(bT, bT)
        }
    }

    function w(z) {
        var bT = this.abs();
        var L = z.t = 2 * bT.t;
        while (--L >= 0) {
            z[L] = 0
        }
        for (L = 0; L < bT.t - 1; ++L) {
            var t = bT.am(L, bT[L], z, 2 * L, 0, 1);
            if ((z[L + bT.t] += bT.am(L + 1, 2 * bT[L], z, 2 * L + 1, t, bT.t - L - 1)) >= bT.DV) {
                z[L + bT.t] -= bT.DV;
                z[L + bT.t + 1] = 1
            }
        }
        if (z.t > 0) {
            z[z.t - 1] += bT.am(L, bT[L], z, 2 * L, 0, 1)
        }
        z.s = 0;
        z.clamp()
    }

    function bC(bZ, bU, L) {
        var b5 = bZ.abs();
        if (b5.t <= 0) {
            return
        }
        var bV = this.abs();
        if (bV.t < b5.t) {
            if (bU != null) {
                bU.fromInt(0)
            }
            if (L != null) {
                this.copyTo(L)
            }
            return
        }
        if (L == null) {
            L = ao()
        }
        var z = ao(),
            bX = this.s,
            bW = bZ.s;
        var b4 = this.DB - H(b5[b5.t - 1]);
        if (b4 > 0) {
            b5.lShiftTo(b4, z);
            bV.lShiftTo(b4, L)
        } else {
            b5.copyTo(z);
            bV.copyTo(L)
        }
        var b1 = z.t;
        var bY = z[b1 - 1];
        if (bY == 0) {
            return
        }
        var b0 = bY * (1 << this.F1) + ((b1 > 1) ? z[b1 - 2] >> this.F2 : 0);
        var b8 = this.FV / b0,
            b7 = (1 << this.F1) / b0,
            b6 = 1 << this.F2;
        var b3 = L.t,
            b2 = b3 - b1,
            bT = (bU == null) ? ao() : bU;
        z.dlShiftTo(b2, bT);
        if (L.compareTo(bT) >= 0) {
            L[L.t++] = 1;
            L.subTo(bT, L)
        }
        T.ONE.dlShiftTo(b1, bT);
        bT.subTo(z, z);
        while (z.t < b1) {
            z[z.t++] = 0
        }
        while (--b2 >= 0) {
            var t = (L[--b3] == bY) ? this.DM : Math.floor(L[b3] * b8 + (L[b3 - 1] + b6) * b7);
            if ((L[b3] += z.am(0, t, L, b2, 0, b1)) < t) {
                z.dlShiftTo(b2, bT);
                L.subTo(bT, L);
                while (L[b3] < --t) {
                    L.subTo(bT, L)
                }
            }
        }
        if (bU != null) {
            L.drShiftTo(b1, bU);
            if (bX != bW) {
                T.ZERO.subTo(bU, bU)
            }
        }
        L.t = b1;
        L.clamp();
        if (b4 > 0) {
            L.rShiftTo(b4, L)
        }
        if (bX < 0) {
            T.ZERO.subTo(L, L)
        }
    }

    function bM(z) {
        var t = ao();
        this.abs().divRemTo(z, null, t);
        if (this.s < 0 && t.compareTo(T.ZERO) > 0) {
            z.subTo(t, t)
        }
        return t
    }

    function bb(t) {
        this.m = t
    }

    function r(t) {
        if (t.s < 0 || t.compareTo(this.m) >= 0) {
            return t.mod(this.m)
        } else {
            return t
        }
    }

    function bA(t) {
        return t
    }

    function aC(t) {
        t.divRemTo(this.m, null, t)
    }

    function J(t, z, L) {
        t.multiplyTo(z, L);
        this.reduce(L)
    }

    function au(z, t) {
        z.squareTo(t);
        this.reduce(t)
    }
    bb.prototype.convert = r;
    bb.prototype.revert = bA;
    bb.prototype.reduce = aC;
    bb.prototype.mulTo = J;
    bb.prototype.sqrTo = au;

    function x() {
        if (this.t < 1) {
            return 0
        }
        var z = this[0];
        if ((z & 1) == 0) {
            return 0
        }
        var t = z & 3;
        t = (t * (2 - (z & 15) * t)) & 15;
        t = (t * (2 - (z & 255) * t)) & 255;
        t = (t * (2 - (((z & 65535) * t) & 65535))) & 65535;
        t = (t * (2 - z * t % this.DV)) % this.DV;
        return (t > 0) ? this.DV - t : -t
    }

    function aG(t) {
        this.m = t;
        this.mp = t.invDigit();
        this.mpl = this.mp & 32767;
        this.mph = this.mp >> 15;
        this.um = (1 << (t.DB - 15)) - 1;
        this.mt2 = 2 * t.t
    }

    function s(z) {
        var t = ao();
        z.abs().dlShiftTo(this.m.t, t);
        t.divRemTo(this.m, null, t);
        if (z.s < 0 && t.compareTo(T.ZERO) > 0) {
            this.m.subTo(t, t)
        }
        return t
    }

    function m(z) {
        var t = ao();
        z.copyTo(t);
        this.reduce(t);
        return t
    }

    function aR(bT) {
        while (bT.t <= this.mt2) {
            bT[bT.t++] = 0
        }
        for (var z = 0; z < this.m.t; ++z) {
            var L = bT[z] & 32767;
            var t = (L * this.mpl + (((L * this.mph + (bT[z] >> 15) * this.mpl) & this.um) << 15)) & bT.DM;
            L = z + this.m.t;
            bT[L] += this.m.am(0, t, bT, z, 0, this.m.t);
            while (bT[L] >= bT.DV) {
                bT[L] -= bT.DV;
                bT[++L]++
            }
        }
        bT.clamp();
        bT.drShiftTo(this.m.t, bT);
        if (bT.compareTo(this.m) >= 0) {
            bT.subTo(this.m, bT)
        }
    }

    function aV(z, t) {
        z.squareTo(t);
        this.reduce(t)
    }

    function e(t, z, L) {
        t.multiplyTo(z, L);
        this.reduce(L)
    }
    aG.prototype.convert = s;
    aG.prototype.revert = m;
    aG.prototype.reduce = aR;
    aG.prototype.mulTo = e;
    aG.prototype.sqrTo = aV;

    function aM() {
        return ((this.t > 0) ? (this[0] & 1) : this.s) == 0
    }

    function R(t, L) {
        if (t > 4294967295 || t < 1) {
            return T.ONE
        }
        var bT = ao(),
            bV = ao(),
            bU = L.convert(this),
            bW = H(t) - 1;
        bU.copyTo(bT);
        while (--bW >= 0) {
            L.sqrTo(bT, bV);
            if ((t & (1 << bW)) > 0) {
                L.mulTo(bV, bU, bT)
            } else {
                var z = bT;
                bT = bV;
                bV = z
            }
        }
        return L.revert(bT)
    }

    function ay(z, L) {
        var t;
        if (z < 256 || L.isEven()) {
            t = new bb(L)
        } else {
            t = new aG(L)
        }
        return this.exp(z, t)
    }
    T.prototype.copyTo = Y;
    T.prototype.fromInt = f;
    T.prototype.fromString = bL;
    T.prototype.clamp = ai;
    T.prototype.dlShiftTo = d;
    T.prototype.drShiftTo = bi;
    T.prototype.lShiftTo = ba;
    T.prototype.rShiftTo = aI;
    T.prototype.subTo = ak;
    T.prototype.multiplyTo = bO;
    T.prototype.squareTo = w;
    T.prototype.divRemTo = bC;
    T.prototype.invDigit = x;
    T.prototype.isEven = aM;
    T.prototype.exp = R;
    T.prototype.toString = a9;
    T.prototype.negate = Q;
    T.prototype.abs = aB;
    T.prototype.compareTo = ah;
    T.prototype.bitLength = c;
    T.prototype.mod = bM;
    T.prototype.modPowInt = ay;
    T.ZERO = aK(0);
    T.ONE = aK(1);

    function M() {
        var t = ao();
        this.copyTo(t);
        return t
    }

    function ae() {
        if (this.s < 0) {
            if (this.t == 1) {
                return this[0] - this.DV
            } else {
                if (this.t == 0) {
                    return -1
                }
            }
        } else {
            if (this.t == 1) {
                return this[0]
            } else {
                if (this.t == 0) {
                    return 0
                }
            }
        }
        return ((this[1] & ((1 << (32 - this.DB)) - 1)) << this.DB) | this[0]
    }

    function bn() {
        return (this.t == 0) ? this.s : (this[0] << 24) >> 24
    }

    function aF() {
        return (this.t == 0) ? this.s : (this[0] << 16) >> 16
    }

    function aa(t) {
        return Math.floor(Math.LN2 * this.DB / Math.log(t))
    }

    function an() {
        if (this.s < 0) {
            return -1
        } else {
            if (this.t <= 0 || (this.t == 1 && this[0] <= 0)) {
                return 0
            } else {
                return 1
            }
        }
    }

    function i(L) {
        if (L == null) {
            L = 10
        }
        if (this.signum() == 0 || L < 2 || L > 36) {
            return "0"
        }
        var bW = this.chunkSize(L);
        var bV = Math.pow(L, bW);
        var bT = aK(bV),
            t = ao(),
            bU = ao(),
            z = "";
        this.divRemTo(bT, t, bU);
        while (t.signum() > 0) {
            z = (bV + bU.intValue()).toString(L).substr(1) + z;
            t.divRemTo(bT, t, bU)
        }
        return bU.intValue().toString(L) + z
    }

    function a3(bY, bV) {
        this.fromInt(0);
        if (bV == null) {
            bV = 10
        }
        var bT = this.chunkSize(bV);
        var bU = Math.pow(bV, bT),
            z = false,
            t = 0,
            bX = 0;
        for (var L = 0; L < bY.length; ++L) {
            var bW = bp(bY, L);
            if (bW < 0) {
                if (bY.charAt(L) == "-" && this.signum() == 0) {
                    z = true
                }
                continue
            }
            bX = bV * bX + bW;
            if (++t >= bT) {
                this.dMultiply(bU);
                this.dAddOffset(bX, 0);
                t = 0;
                bX = 0
            }
        }
        if (t > 0) {
            this.dMultiply(Math.pow(bV, t));
            this.dAddOffset(bX, 0)
        }
        if (z) {
            T.ZERO.subTo(this, this)
        }
    }

    function al(t, bT, L) {
        if ("number" == typeof bT) {
            if (t < 2) {
                this.fromInt(1)
            } else {
                this.fromNumber(t, L);
                if (!this.testBit(t - 1)) {
                    this.bitwiseTo(T.ONE.shiftLeft(t - 1), ap, this)
                }
                if (this.isEven()) {
                    this.dAddOffset(1, 0)
                }
                while (!this.isProbablePrime(bT)) {
                    this.dAddOffset(2, 0);
                    if (this.bitLength() > t) {
                        this.subTo(T.ONE.shiftLeft(t - 1), this)
                    }
                }
            }
        } else {
            var bU = new Array(),
                z = t & 7;
            bU.length = (t >> 3) + 1;
            bT.nextBytes(bU);
            if (z > 0) {
                bU[0] &= ((1 << z) - 1)
            } else {
                bU[0] = 0
            }
            this.fromString(bU, 256)
        }
    }

    function bq() {
        var t = this.t,
            bT = new Array();
        bT[0] = this.s;
        var L = this.DB - (t * this.DB) % 8,
            bU, z = 0;
        if (t-- > 0) {
            if (L < this.DB && (bU = this[t] >> L) != (this.s & this.DM) >> L) {
                bT[z++] = bU | (this.s << (this.DB - L))
            }
            while (t >= 0) {
                if (L < 8) {
                    bU = (this[t] & ((1 << L) - 1)) << (8 - L);
                    bU |= this[--t] >> (L += this.DB - 8)
                } else {
                    bU = (this[t] >> (L -= 8)) & 255;
                    if (L <= 0) {
                        L += this.DB;
                        --t
                    }
                } if ((bU & 128) != 0) {
                    bU |= -256
                }
                if (z == 0 && (this.s & 128) != (bU & 128)) {
                    ++z
                }
                if (z > 0 || bU != this.s) {
                    bT[z++] = bU
                }
            }
        }
        return bT
    }

    function aE(t) {
        return (this.compareTo(t) == 0)
    }

    function P(t) {
        return (this.compareTo(t) < 0) ? this : t
    }

    function bJ(t) {
        return (this.compareTo(t) > 0) ? this : t
    }

    function y(bU, bT, t) {
        var bV, z, L = Math.min(bU.t, this.t);
        for (bV = 0; bV < L; ++bV) {
            t[bV] = bT(this[bV], bU[bV])
        }
        if (bU.t < this.t) {
            z = bU.s & this.DM;
            for (bV = L; bV < this.t; ++bV) {
                t[bV] = bT(this[bV], z)
            }
            t.t = this.t
        } else {
            z = this.s & this.DM;
            for (bV = L; bV < bU.t; ++bV) {
                t[bV] = bT(z, bU[bV])
            }
            t.t = bU.t
        }
        t.s = bT(this.s, bU.s);
        t.clamp()
    }

    function D(z, t) {
        return z & t
    }

    function aA(z) {
        var t = ao();
        this.bitwiseTo(z, D, t);
        return t
    }

    function ap(z, t) {
        return z | t
    }

    function by(z) {
        var t = ao();
        this.bitwiseTo(z, ap, t);
        return t
    }

    function bS(z, t) {
        return z ^ t
    }

    function p(z) {
        var t = ao();
        this.bitwiseTo(z, bS, t);
        return t
    }

    function bQ(z, t) {
        return z & ~t
    }

    function o(z) {
        var t = ao();
        this.bitwiseTo(z, bQ, t);
        return t
    }

    function n() {
        var t = ao();
        for (var z = 0; z < this.t; ++z) {
            t[z] = this.DM & ~this[z]
        }
        t.t = this.t;
        t.s = ~this.s;
        return t
    }

    function aZ(t) {
        var z = ao();
        if (t < 0) {
            this.rShiftTo(-t, z)
        } else {
            this.lShiftTo(t, z)
        }
        return z
    }

    function aj(t) {
        var z = ao();
        if (t < 0) {
            this.lShiftTo(-t, z)
        } else {
            this.rShiftTo(t, z)
        }
        return z
    }

    function l(z) {
        if (z == 0) {
            return -1
        }
        var t = 0;
        if ((z & 65535) == 0) {
            z >>= 16;
            t += 16
        }
        if ((z & 255) == 0) {
            z >>= 8;
            t += 8
        }
        if ((z & 15) == 0) {
            z >>= 4;
            t += 4
        }
        if ((z & 3) == 0) {
            z >>= 2;
            t += 2
        }
        if ((z & 1) == 0) {
            ++t
        }
        return t
    }

    function aS() {
        for (var t = 0; t < this.t; ++t) {
            if (this[t] != 0) {
                return t * this.DB + l(this[t])
            }
        }
        if (this.s < 0) {
            return this.t * this.DB
        }
        return -1
    }

    function F(z) {
        var t = 0;
        while (z != 0) {
            z &= z - 1;
            ++t
        }
        return t
    }

    function bD() {
        var z = 0,
            t = this.s & this.DM;
        for (var L = 0; L < this.t; ++L) {
            z += F(this[L] ^ t)
        }
        return z
    }

    function bt(t) {
        var z = Math.floor(t / this.DB);
        if (z >= this.t) {
            return (this.s != 0)
        }
        return ((this[z] & (1 << (t % this.DB))) != 0)
    }

    function a5(z, L) {
        var t = T.ONE.shiftLeft(z);
        this.bitwiseTo(t, L, t);
        return t
    }

    function a0(t) {
        return this.changeBit(t, ap)
    }

    function g(t) {
        return this.changeBit(t, bQ)
    }

    function a4(t) {
        return this.changeBit(t, bS)
    }

    function bh(t, bT) {
        var L = 0,
            bU = 0,
            z = Math.min(t.t, this.t);
        while (L < z) {
            bU += this[L] + t[L];
            bT[L++] = bU & this.DM;
            bU >>= this.DB
        }
        if (t.t < this.t) {
            bU += t.s;
            while (L < this.t) {
                bU += this[L];
                bT[L++] = bU & this.DM;
                bU >>= this.DB
            }
            bU += this.s
        } else {
            bU += this.s;
            while (L < t.t) {
                bU += t[L];
                bT[L++] = bU & this.DM;
                bU >>= this.DB
            }
            bU += t.s
        }
        bT.s = (bU < 0) ? -1 : 0;
        if (bU > 0) {
            bT[L++] = bU
        } else {
            if (bU < -1) {
                bT[L++] = this.DV + bU
            }
        }
        bT.t = L;
        bT.clamp()
    }

    function at(z) {
        var t = ao();
        this.addTo(z, t);
        return t
    }

    function G(z) {
        var t = ao();
        this.subTo(z, t);
        return t
    }

    function aW(z) {
        var t = ao();
        this.multiplyTo(z, t);
        return t
    }

    function aN() {
        var t = ao();
        this.squareTo(t);
        return t
    }

    function av(z) {
        var t = ao();
        this.divRemTo(z, t, null);
        return t
    }

    function aJ(z) {
        var t = ao();
        this.divRemTo(z, null, t);
        return t
    }

    function bf(t) {
        var z = ao(),
            L = ao();
        this.divRemTo(t, z, L);
        return new Array(z, L)
    }

    function S(t) {
        this[this.t] = this.am(0, t - 1, this, 0, 0, this.t);
        ++this.t;
        this.clamp()
    }

    function C(t, z) {
        if (t == 0) {
            return
        }
        while (this.t <= z) {
            this[this.t++] = 0
        }
        this[z] += t;
        while (this[z] >= this.DV) {
            this[z] -= this.DV;
            if (++z >= this.t) {
                this[this.t++] = 0
            }++this[z]
        }
    }

    function aq() {}

    function U(t) {
        return t
    }

    function K(t, z, L) {
        t.multiplyTo(z, L)
    }

    function bj(z, t) {
        z.squareTo(t)
    }
    aq.prototype.convert = U;
    aq.prototype.revert = U;
    aq.prototype.mulTo = K;
    aq.prototype.sqrTo = bj;

    function br(t) {
        return this.exp(t, new aq())
    }

    function a1(bU, bT, t) {
        var z = Math.min(this.t + bU.t, bT);
        t.s = 0;
        t.t = z;
        while (z > 0) {
            t[--z] = 0
        }
        var L;
        for (L = t.t - this.t; z < L; ++z) {
            t[z + this.t] = this.am(0, bU[z], t, z, 0, this.t)
        }
        for (L = Math.min(bU.t, bT); z < L; ++z) {
            this.am(0, bU[z], t, z, 0, bT - z)
        }
        t.clamp()
    }

    function aX(bT, t, z) {
        --t;
        var L = z.t = this.t + bT.t - t;
        z.s = 0;
        while (--L >= 0) {
            z[L] = 0
        }
        for (L = Math.max(t - this.t, 0); L < bT.t; ++L) {
            z[this.t + L - t] = this.am(t - L, bT[L], z, 0, 0, this.t + L - t)
        }
        z.clamp();
        z.drShiftTo(1, z)
    }

    function bB(t) {
        this.r2 = ao();
        this.q3 = ao();
        T.ONE.dlShiftTo(2 * t.t, this.r2);
        this.mu = this.r2.divide(t);
        this.m = t
    }

    function Z(z) {
        if (z.s < 0 || z.t > 2 * this.m.t) {
            return z.mod(this.m)
        } else {
            if (z.compareTo(this.m) < 0) {
                return z
            } else {
                var t = ao();
                z.copyTo(t);
                this.reduce(t);
                return t
            }
        }
    }

    function E(t) {
        return t
    }

    function I(t) {
        t.drShiftTo(this.m.t - 1, this.r2);
        if (t.t > this.m.t + 1) {
            t.t = this.m.t + 1;
            t.clamp()
        }
        this.mu.multiplyUpperTo(this.r2, this.m.t + 1, this.q3);
        this.m.multiplyLowerTo(this.q3, this.m.t + 1, this.r2);
        while (t.compareTo(this.r2) < 0) {
            t.dAddOffset(1, this.m.t + 1)
        }
        t.subTo(this.r2, t);
        while (t.compareTo(this.m) >= 0) {
            t.subTo(this.m, t)
        }
    }

    function V(z, t) {
        z.squareTo(t);
        this.reduce(t)
    }

    function am(t, z, L) {
        t.multiplyTo(z, L);
        this.reduce(L)
    }
    bB.prototype.convert = Z;
    bB.prototype.revert = E;
    bB.prototype.reduce = I;
    bB.prototype.mulTo = am;
    bB.prototype.sqrTo = V;

    function aT(b1, b2) {
        var bY = b1.bitLength(),
            t, bU = aK(1),
            bV;
        if (bY <= 0) {
            return bU
        } else {
            if (bY < 18) {
                t = 1
            } else {
                if (bY < 48) {
                    t = 3
                } else {
                    if (bY < 144) {
                        t = 4
                    } else {
                        if (bY < 768) {
                            t = 5
                        } else {
                            t = 6
                        }
                    }
                }
            }
        } if (bY < 8) {
            bV = new bb(b2)
        } else {
            if (b2.isEven()) {
                bV = new bB(b2)
            } else {
                bV = new aG(b2)
            }
        }
        var b5 = new Array(),
            b0 = 3,
            b3 = t - 1,
            L = (1 << t) - 1;
        b5[1] = bV.convert(this);
        if (t > 1) {
            var b4 = ao();
            bV.sqrTo(b5[1], b4);
            while (b0 <= L) {
                b5[b0] = ao();
                bV.mulTo(b4, b5[b0 - 2], b5[b0]);
                b0 += 2
            }
        }
        var bW = b1.t - 1,
            bZ, bT = true,
            bX = ao(),
            z;
        bY = H(b1[bW]) - 1;
        while (bW >= 0) {
            if (bY >= b3) {
                bZ = (b1[bW] >> (bY - b3)) & L
            } else {
                bZ = (b1[bW] & ((1 << (bY + 1)) - 1)) << (b3 - bY);
                if (bW > 0) {
                    bZ |= b1[bW - 1] >> (this.DB + bY - b3)
                }
            }
            b0 = t;
            while ((bZ & 1) == 0) {
                bZ >>= 1;
                --b0
            }
            if ((bY -= b0) < 0) {
                bY += this.DB;
                --bW
            }
            if (bT) {
                b5[bZ].copyTo(bU);
                bT = false
            } else {
                while (b0 > 1) {
                    bV.sqrTo(bU, bX);
                    bV.sqrTo(bX, bU);
                    b0 -= 2
                }
                if (b0 > 0) {
                    bV.sqrTo(bU, bX)
                } else {
                    z = bU;
                    bU = bX;
                    bX = z
                }
                bV.mulTo(bX, b5[bZ], bU)
            }
            while (bW >= 0 && (b1[bW] & (1 << bY)) == 0) {
                bV.sqrTo(bU, bX);
                z = bU;
                bU = bX;
                bX = z;
                if (--bY < 0) {
                    bY = this.DB - 1;
                    --bW
                }
            }
        }
        return bV.revert(bU)
    }

    function aD(t) {
        var bU = (this.s < 0) ? this.negate() : this.clone();
        var bT = (t.s < 0) ? t.negate() : t.clone();
        if (bU.compareTo(bT) < 0) {
            var z = bU;
            bU = bT;
            bT = z
        }
        var bV = bU.getLowestSetBit(),
            L = bT.getLowestSetBit();
        if (L < 0) {
            return bU
        }
        if (bV < L) {
            L = bV
        }
        if (L > 0) {
            bU.rShiftTo(L, bU);
            bT.rShiftTo(L, bT)
        }
        while (bU.signum() > 0) {
            if ((bV = bU.getLowestSetBit()) > 0) {
                bU.rShiftTo(bV, bU)
            }
            if ((bV = bT.getLowestSetBit()) > 0) {
                bT.rShiftTo(bV, bT)
            }
            if (bU.compareTo(bT) >= 0) {
                bU.subTo(bT, bU);
                bU.rShiftTo(1, bU)
            } else {
                bT.subTo(bU, bT);
                bT.rShiftTo(1, bT)
            }
        }
        if (L > 0) {
            bT.lShiftTo(L, bT)
        }
        return bT
    }

    function A(t) {
        if (t <= 0) {
            return 0
        }
        var z = this.DV % t,
            L = (this.s < 0) ? t - 1 : 0;
        if (this.t > 0) {
            if (z == 0) {
                L = this[0] % t
            } else {
                for (var bT = this.t - 1; bT >= 0; --bT) {
                    L = (z * L + this[bT]) % t
                }
            }
        }
        return L
    }

    function bd(t) {
        var bX = t.isEven();
        if ((this.isEven() && bX) || t.signum() == 0) {
            return T.ZERO
        }
        var bV = t.clone(),
            bW = this.clone();
        var L = aK(1),
            bT = aK(0),
            z = aK(0),
            bU = aK(1);
        while (bV.signum() != 0) {
            while (bV.isEven()) {
                bV.rShiftTo(1, bV);
                if (bX) {
                    if (!L.isEven() || !bT.isEven()) {
                        L.addTo(this, L);
                        bT.subTo(t, bT)
                    }
                    L.rShiftTo(1, L)
                } else {
                    if (!bT.isEven()) {
                        bT.subTo(t, bT)
                    }
                }
                bT.rShiftTo(1, bT)
            }
            while (bW.isEven()) {
                bW.rShiftTo(1, bW);
                if (bX) {
                    if (!z.isEven() || !bU.isEven()) {
                        z.addTo(this, z);
                        bU.subTo(t, bU)
                    }
                    z.rShiftTo(1, z)
                } else {
                    if (!bU.isEven()) {
                        bU.subTo(t, bU)
                    }
                }
                bU.rShiftTo(1, bU)
            }
            if (bV.compareTo(bW) >= 0) {
                bV.subTo(bW, bV);
                if (bX) {
                    L.subTo(z, L)
                }
                bT.subTo(bU, bT)
            } else {
                bW.subTo(bV, bW);
                if (bX) {
                    z.subTo(L, z)
                }
                bU.subTo(bT, bU)
            }
        }
        if (bW.compareTo(T.ONE) != 0) {
            return T.ZERO
        }
        if (bU.compareTo(t) >= 0) {
            return bU.subtract(t)
        }
        if (bU.signum() < 0) {
            bU.addTo(t, bU)
        } else {
            return bU
        } if (bU.signum() < 0) {
            return bU.add(t)
        } else {
            return bU
        }
    }
    var az = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997];
    var bv = (1 << 26) / az[az.length - 1];

    function j(t) {
        var bT, bU = this.abs();
        if (bU.t == 1 && bU[0] <= az[az.length - 1]) {
            for (bT = 0; bT < az.length; ++bT) {
                if (bU[0] == az[bT]) {
                    return true
                }
            }
            return false
        }
        if (bU.isEven()) {
            return false
        }
        bT = 1;
        while (bT < az.length) {
            var z = az[bT],
                L = bT + 1;
            while (L < az.length && z < bv) {
                z *= az[L++]
            }
            z = bU.modInt(z);
            while (bT < L) {
                if (z % az[bT++] == 0) {
                    return false
                }
            }
        }
        return bU.millerRabin(t)
    }

    function aH(bX) {
        var t = this.subtract(T.ONE);
        var bV = t.getLowestSetBit();
        if (bV <= 0) {
            return false
        }
        var bT = t.shiftRight(bV);
        bX = (bX + 1) >> 1;
        if (bX > az.length) {
            bX = az.length
        }
        var bW = ao();
        for (var L = 0; L < bX; ++L) {
            bW.fromInt(az[Math.floor(Math.random() * az.length)]);
            var z = bW.modPow(bT, this);
            if (z.compareTo(T.ONE) != 0 && z.compareTo(t) != 0) {
                var bU = 1;
                while (bU++ < bV && z.compareTo(t) != 0) {
                    z = z.modPowInt(2, this);
                    if (z.compareTo(T.ONE) == 0) {
                        return false
                    }
                }
                if (z.compareTo(t) != 0) {
                    return false
                }
            }
        }
        return true
    }
    T.prototype.chunkSize = aa;
    T.prototype.toRadix = i;
    T.prototype.fromRadix = a3;
    T.prototype.fromNumber = al;
    T.prototype.bitwiseTo = y;
    T.prototype.changeBit = a5;
    T.prototype.addTo = bh;
    T.prototype.dMultiply = S;
    T.prototype.dAddOffset = C;
    T.prototype.multiplyLowerTo = a1;
    T.prototype.multiplyUpperTo = aX;
    T.prototype.modInt = A;
    T.prototype.millerRabin = aH;
    T.prototype.clone = M;
    T.prototype.intValue = ae;
    T.prototype.byteValue = bn;
    T.prototype.shortValue = aF;
    T.prototype.signum = an;
    T.prototype.toByteArray = bq;
    T.prototype.equals = aE;
    T.prototype.min = P;
    T.prototype.max = bJ;
    T.prototype.and = aA;
    T.prototype.or = by;
    T.prototype.xor = p;
    T.prototype.andNot = o;
    T.prototype.not = n;
    T.prototype.shiftLeft = aZ;
    T.prototype.shiftRight = aj;
    T.prototype.getLowestSetBit = aS;
    T.prototype.bitCount = bD;
    T.prototype.testBit = bt;
    T.prototype.setBit = a0;
    T.prototype.clearBit = g;
    T.prototype.flipBit = a4;
    T.prototype.add = at;
    T.prototype.subtract = G;
    T.prototype.multiply = aW;
    T.prototype.divide = av;
    T.prototype.remainder = aJ;
    T.prototype.divideAndRemainder = bf;
    T.prototype.modPow = aT;
    T.prototype.modInverse = bd;
    T.prototype.pow = br;
    T.prototype.gcd = aD;
    T.prototype.isProbablePrime = j;
    T.prototype.square = aN;

    function bg() {
        this.i = 0;
        this.j = 0;
        this.S = new Array()
    }

    function bk(t) {
        var bT, L, z;
        for (bT = 0; bT < 256; ++bT) {
            this.S[bT] = bT
        }
        L = 0;
        for (bT = 0; bT < 256; ++bT) {
            L = (L + this.S[bT] + t[bT % t.length]) & 255;
            z = this.S[bT];
            this.S[bT] = this.S[L];
            this.S[L] = z
        }
        this.i = 0;
        this.j = 0
    }

    function aU() {
        var t;
        this.i = (this.i + 1) & 255;
        this.j = (this.j + this.S[this.i]) & 255;
        t = this.S[this.i];
        this.S[this.i] = this.S[this.j];
        this.S[this.j] = t;
        return this.S[(t + this.S[this.i]) & 255]
    }
    bg.prototype.init = bk;
    bg.prototype.next = aU;

    function be() {
        return new bg()
    }
    var ab = 256;
    var bP;
    var bz;
    var b;
    if (bz == null) {
        bz = new Array();
        b = 0;
        var bG;
        if (window.crypto && window.crypto.getRandomValues) {
            var a6 = new Uint32Array(256);
            window.crypto.getRandomValues(a6);
            for (bG = 0; bG < a6.length; ++bG) {
                bz[b++] = a6[bG] & 255
            }
        }
        var W = function(t) {
            this.count = this.count || 0;
            if (this.count >= 256 || b >= ab) {
                if (window.removeEventListener) {
                    window.removeEventListener("mousemove", W)
                } else {
                    if (window.detachEvent) {
                        window.detachEvent("onmousemove", W)
                    }
                }
                return
            }
            this.count += 1;
            var z = t.x + t.y;
            bz[b++] = z & 255
        };
        if (window.addEventListener) {
            window.addEventListener("mousemove", W)
        } else {
            if (window.attachEvent) {
                window.attachEvent("onmousemove", W)
            }
        }
    }

    function bI() {
        if (bP == null) {
            bP = be();
            while (b < ab) {
                var t = Math.floor(65536 * Math.random());
                bz[b++] = t & 255
            }
            bP.init(bz);
            for (b = 0; b < bz.length; ++b) {
                bz[b] = 0
            }
            b = 0
        }
        return bP.next()
    }

    function u(t) {
        var z;
        for (z = 0; z < t.length; ++z) {
            t[z] = bI()
        }
    }

    function aL() {}
    aL.prototype.nextBytes = u;

    function bH(t, z) {
        return new T(t, z)
    }

    function bF(z, t) {
        var bT = "";
        var L = 0;
        while (L + t < z.length) {
            bT += z.substring(L, L + t) + "\n";
            L += t
        }
        return bT + z.substring(L, z.length)
    }

    function bN(t) {
        if (t < 16) {
            return "0" + t.toString(16)
        } else {
            return t.toString(16)
        }
    }

    function bs(bW, bT) {
        if (bT < bW.length + 11) {
            console.error("Message too long for RSA");
            return null
        }
        var t = new Array();
        var bV = bW.length - 1;
        while (bV >= 0 && bT > 0) {
            var L = bW.charCodeAt(bV--);
            if (L < 128) {
                t[--bT] = L
            } else {
                if ((L > 127) && (L < 2048)) {
                    t[--bT] = (L & 63) | 128;
                    t[--bT] = (L >> 6) | 192
                } else {
                    t[--bT] = (L & 63) | 128;
                    t[--bT] = ((L >> 6) & 63) | 128;
                    t[--bT] = (L >> 12) | 224
                }
            }
        }
        t[--bT] = 0;
        var bU = new aL();
        var z = new Array();
        while (bT > 2) {
            z[0] = 0;
            while (z[0] == 0) {
                bU.nextBytes(z)
            }
            t[--bT] = z[0]
        }
        t[--bT] = 2;
        t[--bT] = 0;
        return new T(t)
    }

    function aY() {
        this.n = null;
        this.e = 0;
        this.d = null;
        this.p = null;
        this.q = null;
        this.dmp1 = null;
        this.dmq1 = null;
        this.coeff = null
    }

    function bx(t, z) {
        if (t != null && z != null && t.length > 0 && z.length > 0) {
            this.n = bH(t, 16);
            this.e = parseInt(z, 16)
        } else {
            console.error("Invalid RSA public key")
        }
    }

    function a8(t) {
        return t.modPowInt(this.e, this.n)
    }

    function ax(z) {
        var bT = bs(z, (this.n.bitLength() + 7) >> 3);
        if (bT == null) {
            return null
        }
        var t = this.doPublic(bT);
        if (t == null) {
            return null
        }
        var L = t.toString(16);
        if ((L.length & 1) == 0) {
            return L
        } else {
            return "0" + L
        }
    }
    aY.prototype.doPublic = a8;
    aY.prototype.setPublic = bx;
    aY.prototype.encrypt = ax;

    function bl(t, bU) {
        var L = t.toByteArray();
        var bV = 0;
        while (bV < L.length && L[bV] == 0) {
            ++bV
        }
        if (L.length - bV != bU - 1 || L[bV] != 2) {
            return null
        }++bV;
        while (L[bV] != 0) {
            if (++bV >= L.length) {
                return null
            }
        }
        var bT = "";
        while (++bV < L.length) {
            var z = L[bV] & 255;
            if (z < 128) {
                bT += String.fromCharCode(z)
            } else {
                if ((z > 191) && (z < 224)) {
                    bT += String.fromCharCode(((z & 31) << 6) | (L[bV + 1] & 63));
                    ++bV
                } else {
                    bT += String.fromCharCode(((z & 15) << 12) | ((L[bV + 1] & 63) << 6) | (L[bV + 2] & 63));
                    bV += 2
                }
            }
        }
        return bT
    }

    function a2(z, t) {
        var L = new aL();
        var bW = z >> 1;
        this.e = parseInt(t, 16);
        var bT = new T(t, 16);
        for (;;) {
            for (;;) {
                this.p = new T(z - bW, 1, L);
                if (this.p.subtract(T.ONE).gcd(bT).compareTo(T.ONE) == 0 && this.p.isProbablePrime(10)) {
                    break
                }
            }
            for (;;) {
                this.q = new T(bW, 1, L);
                if (this.q.subtract(T.ONE).gcd(bT).compareTo(T.ONE) == 0 && this.q.isProbablePrime(10)) {
                    break
                }
            }
            if (this.p.compareTo(this.q) <= 0) {
                var bY = this.p;
                this.p = this.q;
                this.q = bY
            }
            var bX = this.p.subtract(T.ONE);
            var bU = this.q.subtract(T.ONE);
            var bV = bX.multiply(bU);
            if (bV.gcd(bT).compareTo(T.ONE) == 0) {
                this.n = this.p.multiply(this.q);
                this.d = bT.modInverse(bV);
                this.dmp1 = this.d.mod(bX);
                this.dmq1 = this.d.mod(bU);
                this.coeff = this.q.modInverse(this.p);
                break
            }
        }
    }

    function h(L) {
        var z = bH(L, 16);
        var t = this.doPrivate(z);
        if (t == null) {
            return null
        }
        return bl(t, (this.n.bitLength() + 7) >> 3)
    }
    aY.prototype.generate = a2;
    aY.prototype.decrypt = h;
    (function() {
        var L = function(bY, bW, bX) {
            var bU = new aL();
            var bZ = bY >> 1;
            this.e = parseInt(bW, 16);
            var bT = new T(bW, 16);
            var bV = this;
            var b0 = function() {
                var b3 = function() {
                    if (bV.p.compareTo(bV.q) <= 0) {
                        var b4 = bV.p;
                        bV.p = bV.q;
                        bV.q = b4
                    }
                    var b6 = bV.p.subtract(T.ONE);
                    var b7 = bV.q.subtract(T.ONE);
                    var b5 = b6.multiply(b7);
                    if (b5.gcd(bT).compareTo(T.ONE) == 0) {
                        bV.n = bV.p.multiply(bV.q);
                        bV.d = bT.modInverse(b5);
                        bV.dmp1 = bV.d.mod(b6);
                        bV.dmq1 = bV.d.mod(b7);
                        bV.coeff = bV.q.modInverse(bV.p);
                        setTimeout(function() {
                            bX()
                        }, 0)
                    } else {
                        setTimeout(b0, 0)
                    }
                };
                var b1 = function() {
                    bV.q = ao();
                    bV.q.fromNumberAsync(bZ, 1, bU, function() {
                        bV.q.subtract(T.ONE).gcda(bT, function(b4) {
                            if (b4.compareTo(T.ONE) == 0 && bV.q.isProbablePrime(10)) {
                                setTimeout(b3, 0)
                            } else {
                                setTimeout(b1, 0)
                            }
                        })
                    })
                };
                var b2 = function() {
                    bV.p = ao();
                    bV.p.fromNumberAsync(bY - bZ, 1, bU, function() {
                        bV.p.subtract(T.ONE).gcda(bT, function(b4) {
                            if (b4.compareTo(T.ONE) == 0 && bV.p.isProbablePrime(10)) {
                                setTimeout(b1, 0)
                            } else {
                                setTimeout(b2, 0)
                            }
                        })
                    })
                };
                setTimeout(b2, 0)
            };
            setTimeout(b0, 0)
        };
        aY.prototype.generateAsync = L;
        var t = function(b0, bY) {
            var bZ = (this.s < 0) ? this.negate() : this.clone();
            var bX = (b0.s < 0) ? b0.negate() : b0.clone();
            if (bZ.compareTo(bX) < 0) {
                var bU = bZ;
                bZ = bX;
                bX = bU
            }
            var bT = bZ.getLowestSetBit(),
                bV = bX.getLowestSetBit();
            if (bV < 0) {
                bY(bZ);
                return
            }
            if (bT < bV) {
                bV = bT
            }
            if (bV > 0) {
                bZ.rShiftTo(bV, bZ);
                bX.rShiftTo(bV, bX)
            }
            var bW = function() {
                if ((bT = bZ.getLowestSetBit()) > 0) {
                    bZ.rShiftTo(bT, bZ)
                }
                if ((bT = bX.getLowestSetBit()) > 0) {
                    bX.rShiftTo(bT, bX)
                }
                if (bZ.compareTo(bX) >= 0) {
                    bZ.subTo(bX, bZ);
                    bZ.rShiftTo(1, bZ)
                } else {
                    bX.subTo(bZ, bX);
                    bX.rShiftTo(1, bX)
                } if (!(bZ.signum() > 0)) {
                    if (bV > 0) {
                        bX.lShiftTo(bV, bX)
                    }
                    setTimeout(function() {
                        bY(bX)
                    }, 0)
                } else {
                    setTimeout(bW, 0)
                }
            };
            setTimeout(bW, 10)
        };
        T.prototype.gcda = t;
        var z = function(bV, b0, bY, bX) {
            if ("number" == typeof b0) {
                if (bV < 2) {
                    this.fromInt(1)
                } else {
                    this.fromNumber(bV, bY);
                    if (!this.testBit(bV - 1)) {
                        this.bitwiseTo(T.ONE.shiftLeft(bV - 1), ap, this)
                    }
                    if (this.isEven()) {
                        this.dAddOffset(1, 0)
                    }
                    var bU = this;
                    var bT = function() {
                        bU.dAddOffset(2, 0);
                        if (bU.bitLength() > bV) {
                            bU.subTo(T.ONE.shiftLeft(bV - 1), bU)
                        }
                        if (bU.isProbablePrime(b0)) {
                            setTimeout(function() {
                                bX()
                            }, 0)
                        } else {
                            setTimeout(bT, 0)
                        }
                    };
                    setTimeout(bT, 0)
                }
            } else {
                var bZ = new Array(),
                    bW = bV & 7;
                bZ.length = (bV >> 3) + 1;
                b0.nextBytes(bZ);
                if (bW > 0) {
                    bZ[0] &= ((1 << bW) - 1)
                } else {
                    bZ[0] = 0
                }
                this.fromString(bZ, 256)
            }
        };
        T.prototype.fromNumberAsync = z
    })();
    var ad = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    var a = "=";

    function bK(z) {
        var L;
        var t;
        var bT = "";
        for (L = 0; L + 3 <= z.length; L += 3) {
            t = parseInt(z.substring(L, L + 3), 16);
            bT += ad.charAt(t >> 6) + ad.charAt(t & 63)
        }
        if (L + 1 == z.length) {
            t = parseInt(z.substring(L, L + 1), 16);
            bT += ad.charAt(t << 2)
        } else {
            if (L + 2 == z.length) {
                t = parseInt(z.substring(L, L + 2), 16);
                bT += ad.charAt(t >> 2) + ad.charAt((t & 3) << 4)
            }
        }
        while ((bT.length & 3) > 0) {
            bT += a
        }
        return bT
    }

    function bm(bT) {
        var bU = "";
        var t;
        var z = 0;
        var L;
        for (t = 0; t < bT.length; ++t) {
            if (bT.charAt(t) == a) {
                break
            }
            v = ad.indexOf(bT.charAt(t));
            if (v < 0) {
                continue
            }
            if (z == 0) {
                bU += aQ(v >> 2);
                L = v & 3;
                z = 1
            } else {
                if (z == 1) {
                    bU += aQ((L << 2) | (v >> 4));
                    L = v & 15;
                    z = 2
                } else {
                    if (z == 2) {
                        bU += aQ(L);
                        bU += aQ(v >> 2);
                        L = v & 3;
                        z = 3
                    } else {
                        bU += aQ((L << 2) | (v >> 4));
                        bU += aQ(v & 15);
                        z = 0
                    }
                }
            }
        }
        if (z == 1) {
            bU += aQ(L << 2)
        }
        return bU
    }

    function aO(t) {
        var z = bm(t);
        var L;
        var bT = new Array();
        for (L = 0; 2 * L < z.length; ++L) {
            bT[L] = parseInt(z.substring(2 * L, 2 * L + 2), 16)
        }
        return bT
    }
    var bo = bo || {};
    bo.env = bo.env || {};
    var ac = bo,
        bu = Object.prototype,
        X = "[object Function]",
        O = ["toString", "valueOf"];
    bo.env.parseUA = function(bW) {
        var bV = function(bY) {
            var bZ = 0;
            return parseFloat(bY.replace(/\./g, function() {
                return (bZ++ == 1) ? "" : "."
            }))
        }, bT = navigator,
            t = {
                ie: 0,
                opera: 0,
                gecko: 0,
                webkit: 0,
                chrome: 0,
                mobile: null,
                air: 0,
                ipad: 0,
                iphone: 0,
                ipod: 0,
                ios: null,
                android: 0,
                webos: 0,
                caja: bT && bT.cajaVersion,
                secure: false,
                os: null
            }, L = bW || (navigator && navigator.userAgent),
            bX = window && window.location,
            bU = bX && bX.href,
            z;
        t.secure = bU && (bU.toLowerCase().indexOf("https") === 0);
        if (L) {
            if ((/windows|win32/i).test(L)) {
                t.os = "windows"
            } else {
                if ((/macintosh/i).test(L)) {
                    t.os = "macintosh"
                } else {
                    if ((/rhino/i).test(L)) {
                        t.os = "rhino"
                    }
                }
            } if ((/KHTML/).test(L)) {
                t.webkit = 1
            }
            z = L.match(/AppleWebKit\/([^\s]*)/);
            if (z && z[1]) {
                t.webkit = bV(z[1]);
                if (/ Mobile\//.test(L)) {
                    t.mobile = "Apple";
                    z = L.match(/OS ([^\s]*)/);
                    if (z && z[1]) {
                        z = bV(z[1].replace("_", "."))
                    }
                    t.ios = z;
                    t.ipad = t.ipod = t.iphone = 0;
                    z = L.match(/iPad|iPod|iPhone/);
                    if (z && z[0]) {
                        t[z[0].toLowerCase()] = t.ios
                    }
                } else {
                    z = L.match(/NokiaN[^\/]*|Android \d\.\d|webOS\/\d\.\d/);
                    if (z) {
                        t.mobile = z[0]
                    }
                    if (/webOS/.test(L)) {
                        t.mobile = "WebOS";
                        z = L.match(/webOS\/([^\s]*);/);
                        if (z && z[1]) {
                            t.webos = bV(z[1])
                        }
                    }
                    if (/ Android/.test(L)) {
                        t.mobile = "Android";
                        z = L.match(/Android ([^\s]*);/);
                        if (z && z[1]) {
                            t.android = bV(z[1])
                        }
                    }
                }
                z = L.match(/Chrome\/([^\s]*)/);
                if (z && z[1]) {
                    t.chrome = bV(z[1])
                } else {
                    z = L.match(/AdobeAIR\/([^\s]*)/);
                    if (z) {
                        t.air = z[0]
                    }
                }
            }
            if (!t.webkit) {
                z = L.match(/Opera[\s\/]([^\s]*)/);
                if (z && z[1]) {
                    t.opera = bV(z[1]);
                    z = L.match(/Version\/([^\s]*)/);
                    if (z && z[1]) {
                        t.opera = bV(z[1])
                    }
                    z = L.match(/Opera Mini[^;]*/);
                    if (z) {
                        t.mobile = z[0]
                    }
                } else {
                    z = L.match(/MSIE\s([^;]*)/);
                    if (z && z[1]) {
                        t.ie = bV(z[1])
                    } else {
                        z = L.match(/Gecko\/([^\s]*)/);
                        if (z) {
                            t.gecko = 1;
                            z = L.match(/rv:([^\s\)]*)/);
                            if (z && z[1]) {
                                t.gecko = bV(z[1])
                            }
                        }
                    }
                }
            }
        }
        return t
    };
    bo.env.ua = bo.env.parseUA();
    bo.isFunction = function(t) {
        return (typeof t === "function") || bu.toString.apply(t) === X
    };
    bo._IEEnumFix = (bo.env.ua.ie) ? function(bT, t) {
        var bU, z, L;
        for (bU = 0; bU < O.length; bU = bU + 1) {
            z = O[bU];
            L = t[z];
            if (ac.isFunction(L) && L != bu[z]) {
                bT[z] = L
            }
        }
    } : function() {};
    bo.extend = function(t, bT, bU) {
        if (!bT || !t) {
            throw new Error("extend failed, please check that all dependencies are included.")
        }
        var L = function() {}, z;
        L.prototype = bT.prototype;
        t.prototype = new L();
        t.prototype.constructor = t;
        t.superclass = bT.prototype;
        if (bT.prototype.constructor == bu.constructor) {
            bT.prototype.constructor = bT
        }
        if (bU) {
            for (z in bU) {
                if (ac.hasOwnProperty(bU, z)) {
                    t.prototype[z] = bU[z]
                }
            }
            ac._IEEnumFix(t.prototype, bU)
        }
    };
    if (typeof KJUR == "undefined" || !KJUR) {
        KJUR = {}
    }
    if (typeof KJUR.asn1 == "undefined" || !KJUR.asn1) {
        KJUR.asn1 = {}
    }
    KJUR.asn1.ASN1Util = new function() {
        this.integerToByteHex = function(z) {
            var t = z.toString(16);
            if ((t.length % 2) == 1) {
                t = "0" + t
            }
            return t
        };
        this.bigIntToMinTwosComplementsHex = function(bT) {
            var bX = bT.toString(16);
            if (bX.substr(0, 1) != "-") {
                if (bX.length % 2 == 1) {
                    bX = "0" + bX
                } else {
                    if (!bX.match(/^[0-7]/)) {
                        bX = "00" + bX
                    }
                }
            } else {
                var L = bX.substr(1);
                var bV = L.length;
                if (bV % 2 == 1) {
                    bV += 1
                } else {
                    if (!bX.match(/^[0-7]/)) {
                        bV += 2
                    }
                }
                var t = "";
                for (var bW = 0; bW < bV; bW++) {
                    t += "f"
                }
                var z = new T(t, 16);
                var bU = z.xor(bT).add(T.ONE);
                bX = bU.toString(16).replace(/^-/, "")
            }
            return bX
        }
    };
    KJUR.asn1.ASN1Object = function() {
        var bT = true;
        var t = null;
        var L = "00";
        var bU = "00";
        var z = "";
        this.getLengthHexFromValue = function() {
            if (typeof this.hV == "undefined" || this.hV == null) {
                throw "this.hV is null or undefined."
            }
            if (this.hV.length % 2 == 1) {
                throw "value hex must be even length: n=" + z.length + ",v=" + this.hV
            }
            var bY = this.hV.length / 2;
            var bX = bY.toString(16);
            if (bX.length % 2 == 1) {
                bX = "0" + bX
            }
            if (bY < 128) {
                return bX
            } else {
                var bW = bX.length / 2;
                if (bW > 15) {
                    throw "ASN.1 length too long to represent by 8x: n = " + bY.toString(16)
                }
                var bV = 128 + bW;
                return bV.toString(16) + bX
            }
        };
        this.getEncodedHex = function() {
            if (this.hTLV == null || this.isModified) {
                this.hV = this.getFreshValueHex();
                this.hL = this.getLengthHexFromValue();
                this.hTLV = this.hT + this.hL + this.hV;
                this.isModified = false
            }
            return this.hTLV
        };
        this.getValueHex = function() {
            this.getEncodedHex();
            return this.hV
        };
        this.getFreshValueHex = function() {
            return ""
        }
    };
    KJUR.asn1.DERAbstractString = function(z) {
        KJUR.asn1.DERAbstractString.superclass.constructor.call(this);
        var L = null;
        var t = null;
        this.getString = function() {
            return this.s
        };
        this.setString = function(bT) {
            this.hTLV = null;
            this.isModified = true;
            this.s = bT;
            this.hV = stohex(this.s)
        };
        this.setStringHex = function(bT) {
            this.hTLV = null;
            this.isModified = true;
            this.s = null;
            this.hV = bT
        };
        this.getFreshValueHex = function() {
            return this.hV
        };
        if (typeof z != "undefined") {
            if (typeof z.str != "undefined") {
                this.setString(z.str)
            } else {
                if (typeof z.hex != "undefined") {
                    this.setStringHex(z.hex)
                }
            }
        }
    };
    bo.extend(KJUR.asn1.DERAbstractString, KJUR.asn1.ASN1Object);
    KJUR.asn1.DERAbstractTime = function(z) {
        KJUR.asn1.DERAbstractTime.superclass.constructor.call(this);
        var L = null;
        var t = null;
        this.localDateToUTC = function(bU) {
            utc = bU.getTime() + (bU.getTimezoneOffset() * 60000);
            var bT = new Date(utc);
            return bT
        };
        this.formatDate = function(bY, b0) {
            var bT = this.zeroPadding;
            var bZ = this.localDateToUTC(bY);
            var b1 = String(bZ.getFullYear());
            if (b0 == "utc") {
                b1 = b1.substr(2, 2)
            }
            var bX = bT(String(bZ.getMonth() + 1), 2);
            var b2 = bT(String(bZ.getDate()), 2);
            var bU = bT(String(bZ.getHours()), 2);
            var bV = bT(String(bZ.getMinutes()), 2);
            var bW = bT(String(bZ.getSeconds()), 2);
            return b1 + bX + b2 + bU + bV + bW + "Z"
        };
        this.zeroPadding = function(bU, bT) {
            if (bU.length >= bT) {
                return bU
            }
            return new Array(bT - bU.length + 1).join("0") + bU
        };
        this.getString = function() {
            return this.s
        };
        this.setString = function(bT) {
            this.hTLV = null;
            this.isModified = true;
            this.s = bT;
            this.hV = stohex(this.s)
        };
        this.setByDateValue = function(bU, bW, bY, bX, bZ, bT) {
            var bV = new Date(Date.UTC(bU, bW - 1, bY, bX, bZ, bT, 0));
            this.setByDate(bV)
        };
        this.getFreshValueHex = function() {
            return this.hV
        }
    };
    bo.extend(KJUR.asn1.DERAbstractTime, KJUR.asn1.ASN1Object);
    KJUR.asn1.DERAbstractStructured = function(t) {
        KJUR.asn1.DERAbstractString.superclass.constructor.call(this);
        var z = null;
        this.setByASN1ObjectArray = function(L) {
            this.hTLV = null;
            this.isModified = true;
            this.asn1Array = L
        };
        this.appendASN1Object = function(L) {
            this.hTLV = null;
            this.isModified = true;
            this.asn1Array.push(L)
        };
        this.asn1Array = new Array();
        if (typeof t != "undefined") {
            if (typeof t.array != "undefined") {
                this.asn1Array = t.array
            }
        }
    };
    bo.extend(KJUR.asn1.DERAbstractStructured, KJUR.asn1.ASN1Object);
    KJUR.asn1.DERBoolean = function() {
        KJUR.asn1.DERBoolean.superclass.constructor.call(this);
        this.hT = "01";
        this.hTLV = "0101ff"
    };
    bo.extend(KJUR.asn1.DERBoolean, KJUR.asn1.ASN1Object);
    KJUR.asn1.DERInteger = function(t) {
        KJUR.asn1.DERInteger.superclass.constructor.call(this);
        this.hT = "02";
        this.setByBigInteger = function(z) {
            this.hTLV = null;
            this.isModified = true;
            this.hV = KJUR.asn1.ASN1Util.bigIntToMinTwosComplementsHex(z)
        };
        this.setByInteger = function(z) {
            var L = new T(String(z), 10);
            this.setByBigInteger(L)
        };
        this.setValueHex = function(z) {
            this.hV = z
        };
        this.getFreshValueHex = function() {
            return this.hV
        };
        if (typeof t != "undefined") {
            if (typeof t.bigint != "undefined") {
                this.setByBigInteger(t.bigint)
            } else {
                if (typeof t["int"] != "undefined") {
                    this.setByInteger(t["int"])
                } else {
                    if (typeof t.hex != "undefined") {
                        this.setValueHex(t.hex)
                    }
                }
            }
        }
    };
    bo.extend(KJUR.asn1.DERInteger, KJUR.asn1.ASN1Object);
    KJUR.asn1.DERBitString = function(t) {
        KJUR.asn1.DERBitString.superclass.constructor.call(this);
        this.hT = "03";
        this.setHexValueIncludingUnusedBits = function(z) {
            this.hTLV = null;
            this.isModified = true;
            this.hV = z
        };
        this.setUnusedBitsAndHexValue = function(L, bT) {
            if (L < 0 || 7 < L) {
                throw "unused bits shall be from 0 to 7: u = " + L
            }
            var z = "0" + L;
            this.hTLV = null;
            this.isModified = true;
            this.hV = z + bT
        };
        this.setByBinaryString = function(bV) {
            bV = bV.replace(/0+$/, "");
            var bW = 8 - bV.length % 8;
            if (bW == 8) {
                bW = 0
            }
            for (var L = 0; L <= bW; L++) {
                bV += "0"
            }
            var bT = "";
            for (var L = 0; L < bV.length - 1; L += 8) {
                var z = bV.substr(L, 8);
                var bU = parseInt(z, 2).toString(16);
                if (bU.length == 1) {
                    bU = "0" + bU
                }
                bT += bU
            }
            this.hTLV = null;
            this.isModified = true;
            this.hV = "0" + bW + bT
        };
        this.setByBooleanArray = function(bT) {
            var z = "";
            for (var L = 0; L < bT.length; L++) {
                if (bT[L] == true) {
                    z += "1"
                } else {
                    z += "0"
                }
            }
            this.setByBinaryString(z)
        };
        this.newFalseArray = function(bT) {
            var L = new Array(bT);
            for (var z = 0; z < bT; z++) {
                L[z] = false
            }
            return L
        };
        this.getFreshValueHex = function() {
            return this.hV
        };
        if (typeof t != "undefined") {
            if (typeof t.hex != "undefined") {
                this.setHexValueIncludingUnusedBits(t.hex)
            } else {
                if (typeof t.bin != "undefined") {
                    this.setByBinaryString(t.bin)
                } else {
                    if (typeof t.array != "undefined") {
                        this.setByBooleanArray(t.array)
                    }
                }
            }
        }
    };
    bo.extend(KJUR.asn1.DERBitString, KJUR.asn1.ASN1Object);
    KJUR.asn1.DEROctetString = function(t) {
        KJUR.asn1.DEROctetString.superclass.constructor.call(this, t);
        this.hT = "04"
    };
    bo.extend(KJUR.asn1.DEROctetString, KJUR.asn1.DERAbstractString);
    KJUR.asn1.DERNull = function() {
        KJUR.asn1.DERNull.superclass.constructor.call(this);
        this.hT = "05";
        this.hTLV = "0500"
    };
    bo.extend(KJUR.asn1.DERNull, KJUR.asn1.ASN1Object);
    KJUR.asn1.DERObjectIdentifier = function(z) {
        var L = function(bT) {
            var bU = bT.toString(16);
            if (bU.length == 1) {
                bU = "0" + bU
            }
            return bU
        };
        var t = function(bW) {
            var bV = "";
            var b0 = new T(bW, 10);
            var bZ = b0.toString(2);
            var bT = 7 - bZ.length % 7;
            if (bT == 7) {
                bT = 0
            }
            var bY = "";
            for (var bU = 0; bU < bT; bU++) {
                bY += "0"
            }
            bZ = bY + bZ;
            for (var bU = 0; bU < bZ.length - 1; bU += 7) {
                var bX = bZ.substr(bU, 7);
                if (bU != bZ.length - 7) {
                    bX = "1" + bX
                }
                bV += L(parseInt(bX, 2))
            }
            return bV
        };
        KJUR.asn1.DERObjectIdentifier.superclass.constructor.call(this);
        this.hT = "06";
        this.setValueHex = function(bT) {
            this.hTLV = null;
            this.isModified = true;
            this.s = null;
            this.hV = bT
        };
        this.setValueOidString = function(bV) {
            if (!bV.match(/^[0-9.]+$/)) {
                throw "malformed oid string: " + bV
            }
            var bW = "";
            var bT = bV.split(".");
            var bX = parseInt(bT[0]) * 40 + parseInt(bT[1]);
            bW += L(bX);
            bT.splice(0, 2);
            for (var bU = 0; bU < bT.length; bU++) {
                bW += t(bT[bU])
            }
            this.hTLV = null;
            this.isModified = true;
            this.s = null;
            this.hV = bW
        };
        this.setValueName = function(bU) {
            if (typeof KJUR.asn1.x509.OID.name2oidList[bU] != "undefined") {
                var bT = KJUR.asn1.x509.OID.name2oidList[bU];
                this.setValueOidString(bT)
            } else {
                throw "DERObjectIdentifier oidName undefined: " + bU
            }
        };
        this.getFreshValueHex = function() {
            return this.hV
        };
        if (typeof z != "undefined") {
            if (typeof z.oid != "undefined") {
                this.setValueOidString(z.oid)
            } else {
                if (typeof z.hex != "undefined") {
                    this.setValueHex(z.hex)
                } else {
                    if (typeof z.name != "undefined") {
                        this.setValueName(z.name)
                    }
                }
            }
        }
    };
    bo.extend(KJUR.asn1.DERObjectIdentifier, KJUR.asn1.ASN1Object);
    KJUR.asn1.DERUTF8String = function(t) {
        KJUR.asn1.DERUTF8String.superclass.constructor.call(this, t);
        this.hT = "0c"
    };
    bo.extend(KJUR.asn1.DERUTF8String, KJUR.asn1.DERAbstractString);
    KJUR.asn1.DERNumericString = function(t) {
        KJUR.asn1.DERNumericString.superclass.constructor.call(this, t);
        this.hT = "12"
    };
    bo.extend(KJUR.asn1.DERNumericString, KJUR.asn1.DERAbstractString);
    KJUR.asn1.DERPrintableString = function(t) {
        KJUR.asn1.DERPrintableString.superclass.constructor.call(this, t);
        this.hT = "13"
    };
    bo.extend(KJUR.asn1.DERPrintableString, KJUR.asn1.DERAbstractString);
    KJUR.asn1.DERTeletexString = function(t) {
        KJUR.asn1.DERTeletexString.superclass.constructor.call(this, t);
        this.hT = "14"
    };
    bo.extend(KJUR.asn1.DERTeletexString, KJUR.asn1.DERAbstractString);
    KJUR.asn1.DERIA5String = function(t) {
        KJUR.asn1.DERIA5String.superclass.constructor.call(this, t);
        this.hT = "16"
    };
    bo.extend(KJUR.asn1.DERIA5String, KJUR.asn1.DERAbstractString);
    KJUR.asn1.DERUTCTime = function(t) {
        KJUR.asn1.DERUTCTime.superclass.constructor.call(this, t);
        this.hT = "17";
        this.setByDate = function(z) {
            this.hTLV = null;
            this.isModified = true;
            this.date = z;
            this.s = this.formatDate(this.date, "utc");
            this.hV = stohex(this.s)
        };
        if (typeof t != "undefined") {
            if (typeof t.str != "undefined") {
                this.setString(t.str)
            } else {
                if (typeof t.hex != "undefined") {
                    this.setStringHex(t.hex)
                } else {
                    if (typeof t.date != "undefined") {
                        this.setByDate(t.date)
                    }
                }
            }
        }
    };
    bo.extend(KJUR.asn1.DERUTCTime, KJUR.asn1.DERAbstractTime);
    KJUR.asn1.DERGeneralizedTime = function(t) {
        KJUR.asn1.DERGeneralizedTime.superclass.constructor.call(this, t);
        this.hT = "18";
        this.setByDate = function(z) {
            this.hTLV = null;
            this.isModified = true;
            this.date = z;
            this.s = this.formatDate(this.date, "gen");
            this.hV = stohex(this.s)
        };
        if (typeof t != "undefined") {
            if (typeof t.str != "undefined") {
                this.setString(t.str)
            } else {
                if (typeof t.hex != "undefined") {
                    this.setStringHex(t.hex)
                } else {
                    if (typeof t.date != "undefined") {
                        this.setByDate(t.date)
                    }
                }
            }
        }
    };
    bo.extend(KJUR.asn1.DERGeneralizedTime, KJUR.asn1.DERAbstractTime);
    KJUR.asn1.DERSequence = function(t) {
        KJUR.asn1.DERSequence.superclass.constructor.call(this, t);
        this.hT = "30";
        this.getFreshValueHex = function() {
            var z = "";
            for (var L = 0; L < this.asn1Array.length; L++) {
                var bT = this.asn1Array[L];
                z += bT.getEncodedHex()
            }
            this.hV = z;
            return this.hV
        }
    };
    bo.extend(KJUR.asn1.DERSequence, KJUR.asn1.DERAbstractStructured);
    KJUR.asn1.DERSet = function(t) {
        KJUR.asn1.DERSet.superclass.constructor.call(this, t);
        this.hT = "31";
        this.getFreshValueHex = function() {
            var L = new Array();
            for (var z = 0; z < this.asn1Array.length; z++) {
                var bT = this.asn1Array[z];
                L.push(bT.getEncodedHex())
            }
            L.sort();
            this.hV = L.join("");
            return this.hV
        }
    };
    bo.extend(KJUR.asn1.DERSet, KJUR.asn1.DERAbstractStructured);
    KJUR.asn1.DERTaggedObject = function(t) {
        KJUR.asn1.DERTaggedObject.superclass.constructor.call(this);
        this.hT = "a0";
        this.hV = "";
        this.isExplicit = true;
        this.asn1Object = null;
        this.setASN1Object = function(L, z, bT) {
            this.hT = z;
            this.isExplicit = L;
            this.asn1Object = bT;
            if (this.isExplicit) {
                this.hV = this.asn1Object.getEncodedHex();
                this.hTLV = null;
                this.isModified = true
            } else {
                this.hV = null;
                this.hTLV = bT.getEncodedHex();
                this.hTLV = this.hTLV.replace(/^../, z);
                this.isModified = false
            }
        };
        this.getFreshValueHex = function() {
            return this.hV
        };
        if (typeof t != "undefined") {
            if (typeof t.tag != "undefined") {
                this.hT = t.tag
            }
            if (typeof t.explicit != "undefined") {
                this.isExplicit = t.explicit
            }
            if (typeof t.obj != "undefined") {
                this.asn1Object = t.obj;
                this.setASN1Object(this.isExplicit, this.hT, this.asn1Object)
            }
        }
    };
    bo.extend(KJUR.asn1.DERTaggedObject, KJUR.asn1.ASN1Object);
    (function(L) {
        var t = {}, z;
        t.decode = function(bZ) {
            var bT;
            if (z === L) {
                var bU = "0123456789ABCDEF",
                    bY = " \f\n\r\t\u00A0\u2028\u2029";
                z = [];
                for (bT = 0; bT < 16; ++bT) {
                    z[bU.charAt(bT)] = bT
                }
                bU = bU.toLowerCase();
                for (bT = 10; bT < 16; ++bT) {
                    z[bU.charAt(bT)] = bT
                }
                for (bT = 0; bT < bY.length; ++bT) {
                    z[bY.charAt(bT)] = -1
                }
            }
            var b0 = [],
                bV = 0,
                bX = 0;
            for (bT = 0; bT < bZ.length; ++bT) {
                var bW = bZ.charAt(bT);
                if (bW == "=") {
                    break
                }
                bW = z[bW];
                if (bW == -1) {
                    continue
                }
                if (bW === L) {
                    throw "Illegal character at offset " + bT
                }
                bV |= bW;
                if (++bX >= 2) {
                    b0[b0.length] = bV;
                    bV = 0;
                    bX = 0
                } else {
                    bV <<= 4
                }
            }
            if (bX) {
                throw "Hex encoding incomplete: 4 bits missing"
            }
            return b0
        };
        window.Hex = t
    })();
    (function(L) {
        var t = {}, z;
        t.decode = function(bZ) {
            var bU;
            if (z === L) {
                var bT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",
                    bY = "= \f\n\r\t\u00A0\u2028\u2029";
                z = [];
                for (bU = 0; bU < 64; ++bU) {
                    z[bT.charAt(bU)] = bU
                }
                for (bU = 0; bU < bY.length; ++bU) {
                    z[bY.charAt(bU)] = -1
                }
            }
            var b0 = [];
            var bV = 0,
                bX = 0;
            for (bU = 0; bU < bZ.length; ++bU) {
                var bW = bZ.charAt(bU);
                if (bW == "=") {
                    break
                }
                bW = z[bW];
                if (bW == -1) {
                    continue
                }
                if (bW === L) {
                    throw "Illegal character at offset " + bU
                }
                bV |= bW;
                if (++bX >= 4) {
                    b0[b0.length] = (bV >> 16);
                    b0[b0.length] = (bV >> 8) & 255;
                    b0[b0.length] = bV & 255;
                    bV = 0;
                    bX = 0
                } else {
                    bV <<= 6
                }
            }
            switch (bX) {
                case 1:
                    throw "Base64 encoding incomplete: at least 2 bits missing";
                case 2:
                    b0[b0.length] = (bV >> 10);
                    break;
                case 3:
                    b0[b0.length] = (bV >> 16);
                    b0[b0.length] = (bV >> 8) & 255;
                    break
            }
            return b0
        };
        t.re = /-----BEGIN [^-]+-----([A-Za-z0-9+\/=\s]+)-----END [^-]+-----|begin-base64[^\n]+\n([A-Za-z0-9+\/=\s]+)====/;
        t.unarmor = function(bU) {
            var bT = t.re.exec(bU);
            if (bT) {
                if (bT[1]) {
                    bU = bT[1]
                } else {
                    if (bT[2]) {
                        bU = bT[2]
                    } else {
                        throw "RegExp out of sync"
                    }
                }
            }
            return t.decode(bU)
        };
        window.Base64 = t
    })();
    (function(bU) {
        var bT = 100,
            L = "\u2026",
            t = {
                tag: function(bX, bY) {
                    var bW = document.createElement(bX);
                    bW.className = bY;
                    return bW
                },
                text: function(bW) {
                    return document.createTextNode(bW)
                }
            };

        function z(bW, bX) {
            if (bW instanceof z) {
                this.enc = bW.enc;
                this.pos = bW.pos
            } else {
                this.enc = bW;
                this.pos = bX
            }
        }
        z.prototype.get = function(bW) {
            if (bW === bU) {
                bW = this.pos++
            }
            if (bW >= this.enc.length) {
                throw "Requesting byte offset " + bW + " on a stream of length " + this.enc.length
            }
            return this.enc[bW]
        };
        z.prototype.hexDigits = "0123456789ABCDEF";
        z.prototype.hexByte = function(bW) {
            return this.hexDigits.charAt((bW >> 4) & 15) + this.hexDigits.charAt(bW & 15)
        };
        z.prototype.hexDump = function(b0, bW, bX) {
            var bZ = "";
            for (var bY = b0; bY < bW; ++bY) {
                bZ += this.hexByte(this.get(bY));
                if (bX !== true) {
                    switch (bY & 15) {
                        case 7:
                            bZ += "  ";
                            break;
                        case 15:
                            bZ += "\n";
                            break;
                        default:
                            bZ += " "
                    }
                }
            }
            return bZ
        };
        z.prototype.parseStringISO = function(bZ, bW) {
            var bY = "";
            for (var bX = bZ; bX < bW; ++bX) {
                bY += String.fromCharCode(this.get(bX))
            }
            return bY
        };
        z.prototype.parseStringUTF = function(b0, bW) {
            var bY = "";
            for (var bX = b0; bX < bW;) {
                var bZ = this.get(bX++);
                if (bZ < 128) {
                    bY += String.fromCharCode(bZ)
                } else {
                    if ((bZ > 191) && (bZ < 224)) {
                        bY += String.fromCharCode(((bZ & 31) << 6) | (this.get(bX++) & 63))
                    } else {
                        bY += String.fromCharCode(((bZ & 15) << 12) | ((this.get(bX++) & 63) << 6) | (this.get(bX++) & 63))
                    }
                }
            }
            return bY
        };
        z.prototype.parseStringBMP = function(b1, bX) {
            var b0 = "";
            for (var bZ = b1; bZ < bX; bZ += 2) {
                var bW = this.get(bZ);
                var bY = this.get(bZ + 1);
                b0 += String.fromCharCode((bW << 8) + bY)
            }
            return b0
        };
        z.prototype.reTime = /^((?:1[89]|2\d)?\d\d)(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])([01]\d|2[0-3])(?:([0-5]\d)(?:([0-5]\d)(?:[.,](\d{1,3}))?)?)?(Z|[-+](?:[0]\d|1[0-2])([0-5]\d)?)?$/;
        z.prototype.parseTime = function(bZ, bX) {
            var bY = this.parseStringISO(bZ, bX),
                bW = this.reTime.exec(bY);
            if (!bW) {
                return "Unrecognized time: " + bY
            }
            bY = bW[1] + "-" + bW[2] + "-" + bW[3] + " " + bW[4];
            if (bW[5]) {
                bY += ":" + bW[5];
                if (bW[6]) {
                    bY += ":" + bW[6];
                    if (bW[7]) {
                        bY += "." + bW[7]
                    }
                }
            }
            if (bW[8]) {
                bY += " UTC";
                if (bW[8] != "Z") {
                    bY += bW[8];
                    if (bW[9]) {
                        bY += ":" + bW[9]
                    }
                }
            }
            return bY
        };
        z.prototype.parseInteger = function(b1, bX) {
            var bW = bX - b1;
            if (bW > 4) {
                bW <<= 3;
                var bZ = this.get(b1);
                if (bZ === 0) {
                    bW -= 8
                } else {
                    while (bZ < 128) {
                        bZ <<= 1;
                        --bW
                    }
                }
                return "(" + bW + " bit)"
            }
            var b0 = 0;
            for (var bY = b1; bY < bX; ++bY) {
                b0 = (b0 << 8) | this.get(bY)
            }
            return b0
        };
        z.prototype.parseBitString = function(bZ, b0) {
            var b4 = this.get(bZ),
                b2 = ((b0 - bZ - 1) << 3) - b4,
                bY = "(" + b2 + " bit)";
            if (b2 <= 20) {
                var bX = b4;
                bY += " ";
                for (var b3 = b0 - 1; b3 > bZ; --b3) {
                    var bW = this.get(b3);
                    for (var b1 = bX; b1 < 8; ++b1) {
                        bY += (bW >> b1) & 1 ? "1" : "0"
                    }
                    bX = 0
                }
            }
            return bY
        };
        z.prototype.parseOctetString = function(b0, bX) {
            var bW = bX - b0,
                bZ = "(" + bW + " byte) ";
            if (bW > bT) {
                bX = b0 + bT
            }
            for (var bY = b0; bY < bX; ++bY) {
                bZ += this.hexByte(this.get(bY))
            }
            if (bW > bT) {
                bZ += L
            }
            return bZ
        };
        z.prototype.parseOID = function(bY, b0) {
            var b3 = "",
                bX = 0,
                bW = 0;
            for (var b2 = bY; b2 < b0; ++b2) {
                var b1 = this.get(b2);
                bX = (bX << 7) | (b1 & 127);
                bW += 7;
                if (!(b1 & 128)) {
                    if (b3 === "") {
                        var bZ = bX < 80 ? bX < 40 ? 0 : 1 : 2;
                        b3 = bZ + "." + (bX - bZ * 40)
                    } else {
                        b3 += "." + ((bW >= 31) ? "bigint" : bX)
                    }
                    bX = bW = 0
                }
            }
            return b3
        };

        function bV(bZ, b0, bY, bW, bX) {
            this.stream = bZ;
            this.header = b0;
            this.length = bY;
            this.tag = bW;
            this.sub = bX
        }
        bV.prototype.typeName = function() {
            if (this.tag === bU) {
                return "unknown"
            }
            var bY = this.tag >> 6,
                bW = (this.tag >> 5) & 1,
                bX = this.tag & 31;
            switch (bY) {
                case 0:
                    switch (bX) {
                        case 0:
                            return "EOC";
                        case 1:
                            return "BOOLEAN";
                        case 2:
                            return "INTEGER";
                        case 3:
                            return "BIT_STRING";
                        case 4:
                            return "OCTET_STRING";
                        case 5:
                            return "NULL";
                        case 6:
                            return "OBJECT_IDENTIFIER";
                        case 7:
                            return "ObjectDescriptor";
                        case 8:
                            return "EXTERNAL";
                        case 9:
                            return "REAL";
                        case 10:
                            return "ENUMERATED";
                        case 11:
                            return "EMBEDDED_PDV";
                        case 12:
                            return "UTF8String";
                        case 16:
                            return "SEQUENCE";
                        case 17:
                            return "SET";
                        case 18:
                            return "NumericString";
                        case 19:
                            return "PrintableString";
                        case 20:
                            return "TeletexString";
                        case 21:
                            return "VideotexString";
                        case 22:
                            return "IA5String";
                        case 23:
                            return "UTCTime";
                        case 24:
                            return "GeneralizedTime";
                        case 25:
                            return "GraphicString";
                        case 26:
                            return "VisibleString";
                        case 27:
                            return "GeneralString";
                        case 28:
                            return "UniversalString";
                        case 30:
                            return "BMPString";
                        default:
                            return "Universal_" + bX.toString(16)
                    }
                case 1:
                    return "Application_" + bX.toString(16);
                case 2:
                    return "[" + bX + "]";
                case 3:
                    return "Private_" + bX.toString(16)
            }
        };
        bV.prototype.reSeemsASCII = /^[ -~]+$/;
        bV.prototype.content = function() {
            if (this.tag === bU) {
                return null
            }
            var b0 = this.tag >> 6,
                bX = this.tag & 31,
                bZ = this.posContent(),
                bW = Math.abs(this.length);
            if (b0 !== 0) {
                if (this.sub !== null) {
                    return "(" + this.sub.length + " elem)"
                }
                var bY = this.stream.parseStringISO(bZ, bZ + Math.min(bW, bT));
                if (this.reSeemsASCII.test(bY)) {
                    return bY.substring(0, 2 * bT) + ((bY.length > 2 * bT) ? L : "")
                } else {
                    return this.stream.parseOctetString(bZ, bZ + bW)
                }
            }
            switch (bX) {
                case 1:
                    return (this.stream.get(bZ) === 0) ? "false" : "true";
                case 2:
                    return this.stream.parseInteger(bZ, bZ + bW);
                case 3:
                    return this.sub ? "(" + this.sub.length + " elem)" : this.stream.parseBitString(bZ, bZ + bW);
                case 4:
                    return this.sub ? "(" + this.sub.length + " elem)" : this.stream.parseOctetString(bZ, bZ + bW);
                case 6:
                    return this.stream.parseOID(bZ, bZ + bW);
                case 16:
                case 17:
                    return "(" + this.sub.length + " elem)";
                case 12:
                    return this.stream.parseStringUTF(bZ, bZ + bW);
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 26:
                    return this.stream.parseStringISO(bZ, bZ + bW);
                case 30:
                    return this.stream.parseStringBMP(bZ, bZ + bW);
                case 23:
                case 24:
                    return this.stream.parseTime(bZ, bZ + bW)
            }
            return null
        };
        bV.prototype.toString = function() {
            return this.typeName() + "@" + this.stream.pos + "[header:" + this.header + ",length:" + this.length + ",sub:" + ((this.sub === null) ? "null" : this.sub.length) + "]"
        };
        bV.prototype.print = function(bX) {
            if (bX === bU) {
                bX = ""
            }
            document.writeln(bX + this);
            if (this.sub !== null) {
                bX += "  ";
                for (var bY = 0, bW = this.sub.length; bY < bW; ++bY) {
                    this.sub[bY].print(bX)
                }
            }
        };
        bV.prototype.toPrettyString = function(bX) {
            if (bX === bU) {
                bX = ""
            }
            var bZ = bX + this.typeName() + " @" + this.stream.pos;
            if (this.length >= 0) {
                bZ += "+"
            }
            bZ += this.length;
            if (this.tag & 32) {
                bZ += " (constructed)"
            } else {
                if (((this.tag == 3) || (this.tag == 4)) && (this.sub !== null)) {
                    bZ += " (encapsulates)"
                }
            }
            bZ += "\n";
            if (this.sub !== null) {
                bX += "  ";
                for (var bY = 0, bW = this.sub.length; bY < bW; ++bY) {
                    bZ += this.sub[bY].toPrettyString(bX)
                }
            }
            return bZ
        };
        bV.prototype.toDOM = function() {
            var b2 = t.tag("div", "node");
            b2.asn1 = this;
            var bY = t.tag("div", "head");
            var b0 = this.typeName().replace(/_/g, " ");
            bY.innerHTML = b0;
            var bW = this.content();
            if (bW !== null) {
                bW = String(bW).replace(/</g, "&lt;");
                var b5 = t.tag("span", "preview");
                b5.appendChild(t.text(bW));
                bY.appendChild(b5)
            }
            b2.appendChild(bY);
            this.node = b2;
            this.head = bY;
            var bZ = t.tag("div", "value");
            b0 = "Offset: " + this.stream.pos + "<br/>";
            b0 += "Length: " + this.header + "+";
            if (this.length >= 0) {
                b0 += this.length
            } else {
                b0 += (-this.length) + " (undefined)"
            } if (this.tag & 32) {
                b0 += "<br/>(constructed)"
            } else {
                if (((this.tag == 3) || (this.tag == 4)) && (this.sub !== null)) {
                    b0 += "<br/>(encapsulates)"
                }
            } if (bW !== null) {
                b0 += "<br/>Value:<br/><b>" + bW + "</b>";
                if ((typeof oids === "object") && (this.tag == 6)) {
                    var b3 = oids[bW];
                    if (b3) {
                        if (b3.d) {
                            b0 += "<br/>" + b3.d
                        }
                        if (b3.c) {
                            b0 += "<br/>" + b3.c
                        }
                        if (b3.w) {
                            b0 += "<br/>(warning!)"
                        }
                    }
                }
            }
            bZ.innerHTML = b0;
            b2.appendChild(bZ);
            var b1 = t.tag("div", "sub");
            if (this.sub !== null) {
                for (var b4 = 0, bX = this.sub.length; b4 < bX; ++b4) {
                    b1.appendChild(this.sub[b4].toDOM())
                }
            }
            b2.appendChild(b1);
            bY.onclick = function() {
                b2.className = (b2.className == "node collapsed") ? "node" : "node collapsed"
            };
            return b2
        };
        bV.prototype.posStart = function() {
            return this.stream.pos
        };
        bV.prototype.posContent = function() {
            return this.stream.pos + this.header
        };
        bV.prototype.posEnd = function() {
            return this.stream.pos + this.header + Math.abs(this.length)
        };
        bV.prototype.fakeHover = function(bW) {
            this.node.className += " hover";
            if (bW) {
                this.head.className += " hover"
            }
        };
        bV.prototype.fakeOut = function(bX) {
            var bW = / ?hover/;
            this.node.className = this.node.className.replace(bW, "");
            if (bX) {
                this.head.className = this.head.className.replace(bW, "")
            }
        };
        bV.prototype.toHexDOM_sub = function(bZ, bY, b0, b1, bW) {
            if (b1 >= bW) {
                return
            }
            var bX = t.tag("span", bY);
            bX.appendChild(t.text(b0.hexDump(b1, bW)));
            bZ.appendChild(bX)
        };
        bV.prototype.toHexDOM = function(bX) {
            var b0 = t.tag("span", "hex");
            if (bX === bU) {
                bX = b0
            }
            this.head.hexNode = b0;
            this.head.onmouseover = function() {
                this.hexNode.className = "hexCurrent"
            };
            this.head.onmouseout = function() {
                this.hexNode.className = "hex"
            };
            b0.asn1 = this;
            b0.onmouseover = function() {
                var b2 = !bX.selected;
                if (b2) {
                    bX.selected = this.asn1;
                    this.className = "hexCurrent"
                }
                this.asn1.fakeHover(b2)
            };
            b0.onmouseout = function() {
                var b2 = (bX.selected == this.asn1);
                this.asn1.fakeOut(b2);
                if (b2) {
                    bX.selected = null;
                    this.className = "hex"
                }
            };
            this.toHexDOM_sub(b0, "tag", this.stream, this.posStart(), this.posStart() + 1);
            this.toHexDOM_sub(b0, (this.length >= 0) ? "dlen" : "ulen", this.stream, this.posStart() + 1, this.posContent());
            if (this.sub === null) {
                b0.appendChild(t.text(this.stream.hexDump(this.posContent(), this.posEnd())))
            } else {
                if (this.sub.length > 0) {
                    var b1 = this.sub[0];
                    var bZ = this.sub[this.sub.length - 1];
                    this.toHexDOM_sub(b0, "intro", this.stream, this.posContent(), b1.posStart());
                    for (var bY = 0, bW = this.sub.length; bY < bW; ++bY) {
                        b0.appendChild(this.sub[bY].toHexDOM(bX))
                    }
                    this.toHexDOM_sub(b0, "outro", this.stream, bZ.posEnd(), this.posEnd())
                }
            }
            return b0
        };
        bV.prototype.toHexString = function(bW) {
            return this.stream.hexDump(this.posStart(), this.posEnd(), true)
        };
        bV.decodeLength = function(bZ) {
            var bX = bZ.get(),
                bW = bX & 127;
            if (bW == bX) {
                return bW
            }
            if (bW > 3) {
                throw "Length over 24 bits not supported at position " + (bZ.pos - 1)
            }
            if (bW === 0) {
                return -1
            }
            bX = 0;
            for (var bY = 0; bY < bW; ++bY) {
                bX = (bX << 8) | bZ.get()
            }
            return bX
        };
        bV.hasContent = function(bX, bW, b2) {
            if (bX & 32) {
                return true
            }
            if ((bX < 3) || (bX > 4)) {
                return false
            }
            var b1 = new z(b2);
            if (bX == 3) {
                b1.get()
            }
            var b0 = b1.get();
            if ((b0 >> 6) & 1) {
                return false
            }
            try {
                var bZ = bV.decodeLength(b1);
                return ((b1.pos - b2.pos) + bZ == bW)
            } catch (bY) {
                return false
            }
        };
        bV.decode = function(bY) {
            if (!(bY instanceof z)) {
                bY = new z(bY, 0)
            }
            var bX = new z(bY),
                b0 = bY.get(),
                b5 = bV.decodeLength(bY),
                b4 = bY.pos - bX.pos,
                b1 = null;
            if (bV.hasContent(b0, b5, bY)) {
                var b2 = bY.pos;
                if (b0 == 3) {
                    bY.get()
                }
                b1 = [];
                if (b5 >= 0) {
                    var b3 = b2 + b5;
                    while (bY.pos < b3) {
                        b1[b1.length] = bV.decode(bY)
                    }
                    if (bY.pos != b3) {
                        throw "Content size is not correct for container starting at offset " + b2
                    }
                } else {
                    try {
                        for (;;) {
                            var bZ = bV.decode(bY);
                            if (bZ.tag === 0) {
                                break
                            }
                            b1[b1.length] = bZ
                        }
                        b5 = b2 - bY.pos
                    } catch (bW) {
                        throw "Exception while decoding undefined length content: " + bW
                    }
                }
            } else {
                bY.pos += b5
            }
            return new bV(bX, b4, b5, b0, b1)
        };
        bV.test = function() {
            var b1 = [{
                value: [39],
                expected: 39
            }, {
                value: [129, 201],
                expected: 201
            }, {
                value: [131, 254, 220, 186],
                expected: 16702650
            }];
            for (var bY = 0, bW = b1.length; bY < bW; ++bY) {
                var b0 = 0,
                    bZ = new z(b1[bY].value, 0),
                    bX = bV.decodeLength(bZ);
                if (bX != b1[bY].expected) {
                    document.write("In test[" + bY + "] expected " + b1[bY].expected + " got " + bX + "\n")
                }
            }
        };
        window.ASN1 = bV
    })();
    ASN1.prototype.getHexStringValue = function() {
        var t = this.toHexString();
        var z = this.header * 2;
        var L = this.length * 2;
        return t.substr(z, L)
    };
    aY.prototype.parseKey = function(b4) {
        try {
            var t = 0;
            var b2 = 0;
            var b0 = /^\s*(?:[0-9A-Fa-f][0-9A-Fa-f]\s*)+$/;
            var b1 = b0.test(b4) ? Hex.decode(b4) : Base64.unarmor(b4);
            var bT = ASN1.decode(b1);
            if (bT.sub.length === 9) {
                t = bT.sub[1].getHexStringValue();
                this.n = bH(t, 16);
                b2 = bT.sub[2].getHexStringValue();
                this.e = parseInt(b2, 16);
                var L = bT.sub[3].getHexStringValue();
                this.d = bH(L, 16);
                var bZ = bT.sub[4].getHexStringValue();
                this.p = bH(bZ, 16);
                var bV = bT.sub[5].getHexStringValue();
                this.q = bH(bV, 16);
                var b3 = bT.sub[6].getHexStringValue();
                this.dmp1 = bH(b3, 16);
                var bU = bT.sub[7].getHexStringValue();
                this.dmq1 = bH(bU, 16);
                var bW = bT.sub[8].getHexStringValue();
                this.coeff = bH(bW, 16)
            } else {
                if (bT.sub.length === 2) {
                    var bY = bT.sub[1];
                    var z = bY.sub[0];
                    t = z.sub[0].getHexStringValue();
                    this.n = bH(t, 16);
                    b2 = z.sub[1].getHexStringValue();
                    this.e = parseInt(b2, 16)
                } else {
                    return false
                }
            }
            return true
        } catch (bX) {
            return false
        }
    };
    aY.prototype.getPublicBaseKey = function() {
        var bT = {
            array: [new KJUR.asn1.DERObjectIdentifier({
                oid: "1.2.840.113549.1.1.1"
            }), new KJUR.asn1.DERNull()]
        };
        var bU = new KJUR.asn1.DERSequence(bT);
        bT = {
            array: [new KJUR.asn1.DERInteger({
                bigint: this.n
            }), new KJUR.asn1.DERInteger({
                "int": this.e
            })]
        };
        var t = new KJUR.asn1.DERSequence(bT);
        bT = {
            hex: "00" + t.getEncodedHex()
        };
        var z = new KJUR.asn1.DERBitString(bT);
        bT = {
            array: [bU, z]
        };
        var L = new KJUR.asn1.DERSequence(bT);
        return L.getEncodedHex()
    };
    aY.prototype.getPublicBaseKeyB64 = function() {
        return bK(this.getPublicBaseKey())
    };
    aY.prototype.wordwrap = function(z, t) {
        t = t || 64;
        if (!z) {
            return z
        }
        var L = "(.{1," + t + "})( +|$\n?)|(.{1," + t + "})";
        return z.match(RegExp(L, "g")).join("\n")
    };
    aY.prototype.getPublicKey = function() {
        var t = "-----BEGIN PUBLIC KEY-----\n";
        t += this.wordwrap(this.getPublicBaseKeyB64()) + "\n";
        t += "-----END PUBLIC KEY-----";
        return t
    };
    aY.prototype.hasPublicKeyProperty = function(t) {
        t = t || {};
        return (t.hasOwnProperty("n") && t.hasOwnProperty("e"))
    };
    aY.prototype.parsePropertiesFrom = function(t) {
        this.n = t.n;
        this.e = t.e;
        if (t.hasOwnProperty("d")) {
            this.d = t.d;
            this.p = t.p;
            this.q = t.q;
            this.dmp1 = t.dmp1;
            this.dmq1 = t.dmq1;
            this.coeff = t.coeff
        }
    };
    var bE = function(t) {
        aY.call(this);
        if (t) {
            if (typeof t === "string") {
                this.parseKey(t)
            } else {
                if (this.hasPublicKeyProperty(t)) {
                    this.parsePropertiesFrom(t)
                }
            }
        }
    };
    bE.prototype = new aY();
    bE.prototype.constructor = bE;
    var ag = function(t) {
        t = t || {};
        this.default_key_size = parseInt(t.default_key_size) || 1024;
        this.default_public_exponent = t.default_public_exponent || "010001";
        this.log = t.log || false;
        this.key = null
    };
    ag.prototype.setKey = function(t) {
        if (this.log && this.key) {
            console.warn("A key was already set, overriding existing.")
        }
        this.key = new bE(t)
    };
    ag.prototype.setPublicKey = function(t) {
        this.setKey(t)
    };
    ag.prototype.decrypt = function(z) {
        try {
            return this.getKey().decrypt(bm(z))
        } catch (t) {
            return false
        }
    };
    ag.prototype.encrypt = function(z) {
        try {
            return bK(this.getKey().encrypt(z))
        } catch (t) {
            return false
        }
    };
    ag.prototype.getKey = function(t) {
        if (!this.key) {
            this.key = new bE();
            if (t && {}.toString.call(t) === "[object Function]") {
                this.key.generateAsync(this.default_key_size, this.default_public_exponent, t);
                return
            }
            this.key.generate(this.default_key_size, this.default_public_exponent)
        }
        return this.key
    };
    ag.prototype.getPublicKey = function() {
        return this.getKey().getPublicKey()
    };
    ag.prototype.getPublicKeyB64 = function() {
        return this.getKey().getPublicBaseKeyB64()
    };
    bw.JSEncrypt = ag
})(JSEncryptExports);
var JSEncrypt = JSEncryptExports.JSEncrypt;
var jsRegistFed = {
    ieLower: $.browser.msie && $.browser.version == 6 || false,
    helpCenterHover: function() {
        $(".help_wrap", ".regist_header_right ").hover(function() {
            $(this).addClass("help_wrap_hover")
        }, function() {
            $(this).removeClass("help_wrap_hover")
        })
    },
    registForm: function(b) {
        if ($(b).length <= 0) {
            return
        }
        var a = $(b).val();
        var c = $(".regist_account_info");
        $(b, ".regist_form").focus(function() {
            if ($(this).val() == $(this).context.defaultValue && $("#lockEmail").val() != 1) {
                $(this).val("").removeClass("gay_text")
            }
            $(this).parents("li").removeClass("cur_error").addClass("cur")
        });
        $(b, ".regist_form").blur(function() {
            var d = $(this).val();
            if (!d) {
                $(this).val($(this).context.defaultValue).addClass("gay_text")
            }
            $(this).parents("li").removeClass("cur")
        });
        $(".form_item").delegate(".ipt_username", "keyup", function() {
            $(this).next(".associat_input").end().parents("li").css("z-index", "103");
            c.css({
                position: "relative",
                "z-index": "203"
            })
        });
        $(document).bind("click", function(e) {
            var d = e.target;
            if (d.className != "ipt_username" || d.className != "associat_input") {
                $(".associat_input").hide();
                c.removeAttr("style")
            }
        })
    },
    serviceAgreement: function() {
        $(".check_agreement", ".service_agreement").click(function() {
            if ($(this).hasClass("uncheck_agreement")) {
                $(this).attr("class", "check_agreement");
                $(this).next(".agreement_tips").hide()
            } else {
                $(this).attr("class", "uncheck_agreement");
                $(this).next(".agreement_tips").show()
            }
            return false
        })
    },
    changeNickName: function() {
        var a = $("#nickname").val();
        var b;
        $("a", ".nickname_default").click(function() {
            $(".nickname_default").hide();
            $(".change_nickname_detail").show();
            $(".change_nickname_detail").delegate("input", "focus", function() {
                if (a == $(this).val()) {
                    $(this).val("")
                }
                $(this).removeClass("gay_text")
            });
            $(".change_nickname_detail").delegate("input", "blur", function() {
                b = $(this).val();
                if (!b) {
                    $(this).val(a);
                    $(this).addClass("gay_text")
                }
            });
            $(".change_nickname_detail").delegate(".save_btn", "click", function() {
                var d = /[\"<>$+]/;
                var e = $("#nickname").val();
                if (e == "") {
                    $("#nickNameDiv").addClass("nichname_wrong");
                    return false
                }
                if (d.test(e)) {
                    $("#nickNameDiv").addClass("nichname_wrong");
                    return false
                }
                if (e.length > 500) {
                    $("#nickNameDiv").addClass("nichname_wrong");
                    return false
                }
                var c = false;
                $.ajax({
                    type: "POST",
                    url: "/passport/updateNickName.do",
                    async: false,
                    data: {
                        nickName: e
                    },
                    success: function(f) {
                        if (f.errorCode == 0) {
                            c = true
                        } else {
                            $("#nickNameDiv").addClass("nichname_wrong")
                        }
                    }
                });
                if (c) {
                    $(this).parents(".change_nickname_detail").hide().next(".your_nickname").show().find(".nickname").text(b);
                    $(this).parents(".change_nickname_detail").prev(".change_nickname").hide()
                }
                return false
            })
        })
    },
    emailReceive: function() {
        $(".no_email_detail").delegate(".no_email", "click", function() {
            $(this).next("ul").show();
            return false
        })
    },
    rate: function(f, a) {
        var j = document.getElementById(a);
        if (null == j) {
            return
        }
        var k = j.style;
        var b = !-[1, ];
        if (b) {
            var c = f * Math.PI / 180,
                m = Math.cos(c),
                l = -Math.sin(c),
                e = Math.sin(c),
                d = Math.cos(c);
            j.fw = j.fw || j.offsetWidth / 2;
            j.fh = j.fh || j.offsetHeight / 2;
            var g = (90 - f % 90) * Math.PI / 180,
                i = Math.sin(g) + Math.cos(g);
            k.filter = "progid:DXImageTransform.Microsoft.Matrix(M11=" + m + ",M12=" + l + ",M21=" + e + ",M22=" + d + ",SizingMethod='auto expand');";
            k.top = j.fh * (1 - i) + "px";
            k.left = j.fw * (1 - i) + "px"
        } else {
            var h = "rotate(" + f + "deg)";
            k.MozTransform = h;
            k.WebkitTransform = h;
            k.OTransform = h;
            k.msTransform = h;
            k.Transform = h
        }
        return false
    },
    paswdStrength: function(a) {
        if ($(a).length <= 0) {
            return
        }
        if ($(a + "2").length <= 0) {
            return
        }
        $(a + "2").attr("readonly", "readonly").css("background-color", "#C0C1C4");
        $(a).focus(function() {
            $(a).parents("li").removeClass("cur_error").addClass("cur")
        });
        $(a + "2").focus(function() {
            $(a + "2").parents("li").removeClass("cur_error").addClass("cur")
        });
        $(".form_item").delegate("input[name='pwd']", "click", function() {
            $(this).hide().next("input[type='password']").show().focus();
            $(this).parents("li").removeClass("cur_error").addClass("cur")
        });
        $("input[name='pwd']").focus(function() {
            $(this).hide().next("input[type='password']").show().focus();
            $(this).parents("li").removeClass("cur_error").addClass("cur")
        });
        $(".form_item").delegate("input[type='password']", "blur", function() {
            var b = $(this).val();
            if (!b) {
                $(this).hide().prev("input[type='text']").show()
            }
            $(this).parents("li").removeClass("cur")
        });
        $(".form_item").delegate(a, "keyup", function() {
            liItem = $(this).parents("li");
            arrowId = liItem.find("i").attr("id");
            liItem.find(".paswd_strength").show();
            var c = $(this).val().length;
            var b = getPassPoint(a);
            if (c == 1) {
                jsRegistFed.rate(0, arrowId)
            } else {
                if (c > 1 && c < 4) {
                    jsRegistFed.rate(30, arrowId)
                } else {
                    if (b >= 80) {
                        jsRegistFed.rate(150, arrowId)
                    } else {
                        if (b >= 50) {
                            jsRegistFed.rate(90, arrowId)
                        }
                    }
                }
            }
        })
    },
    receiveCode: function() {
        $(".phone_verifica_form").delegate(".receive_code", "click", function() {
            if ($(".receive_code").hasClass("reacquire_code")) {
                return false
            }
            $.ajax({
                type: "POST",
                url: "/passport/sendMobileCheckCode.do",
                async: false,
                success: function(a) {
                    if (a) {
                        if (0 == a.errorCode) {
                            var d = $(".receive_code");
                            d.addClass("reacquire_code").html("重新获取验证码(<i>59</i>)");
                            var f = $("i", ".reacquire_code").text();
                            var c = setInterval(function() {
                                if (f > 0) {
                                    f--;
                                    $("i", ".reacquire_code").text(f)
                                }
                            }, 1000);
                            var b = setTimeout(function() {
                                $(".receive_code", ".phone_verifica_form").removeClass("reacquire_code").html("重新获取验证码")
                            }, f * 1000);
                            return
                        } else {
                            if (1000 == a.errorCode) {
                                var h = $(".tips");
                                var g = new Tips(h, "每天只有5次获取验证码的机会");
                                g.show();
                                return
                            } else {
                                if (1001 == a.errorCode) {
                                    var h = $(".tips");
                                    var g = new Tips(h, "短信发送太频繁了");
                                    g.show()
                                } else {
                                    if (1002 == a.errorCode) {
                                        var h = $(".tips");
                                        var g = new Tips(h, "短信发送失败");
                                        g.show()
                                    }
                                }
                            }
                        }
                    }
                }
            });
            return false
        })
    },
    registTab: function() {
        $(".regist_tab").delegate("li", "click", function() {
            var b = $("li", ".regist_tab"),
                a = b.index(this);
            if (a == 1) {
                $(".regist_tab .cur_tab").animate({
                    left: "258px"
                }, 300, function() {
                    $(this).addClass("cur").siblings("li").removeClass("cur");
                    $(".regist_form", ".mod_regist_wrap").eq(a).show().siblings(".regist_form").hide()
                })
            } else {
                $(".regist_tab .cur_tab").animate({
                    left: "0"
                }, 300, function() {
                    $(this).addClass("cur").siblings("li").removeClass("cur");
                    $(".regist_form", ".mod_regist_wrap").eq(a).show().siblings(".regist_form").hide()
                })
            }
        })
    },
    successRotate: function() {
        var a = setTimeout(function() {
            $(".success_rotate").addClass("rating")
        }, 1000)
    },
    areaSelect: function() {
        $(".company_area").parents("li").css("z-index", "200")
    },
    mobileRecvCodeLeft: -350,
    mobileValidCodeLeft: 0,
    reSetValidCodeFlage: false,
    getMobileRecvCode: function(b) {
        var a = false;
        $.ajax({
            type: "POST",
            url: "/passport/sendCheckCodeForRegister.do",
            async: true,
            data: {
                "user.cellphone": $("#phone").val(),
                validCode: $("#validCodeMobile").val(),
                sig: $("#validateSig").val()
            },
            success: function(c) {
                if (c.errorCode == 1) {
                    showPhoneError("不能为空")
                } else {
                    if (c.errorCode == 20) {
                        $(".regist_form .recv_mobile_code").addClass("cur_error");
                        $("#mobile_validcode_error").addClass("regist_tips_error");
                        $("#mobile_validcode_error").find("p").text("验证码错误");
                        refresh_valid_code(window, mobile_captcha_callback);
                        if (showValidCodeWhenRegistByMobile == 0) {
                            showValidCodeWhenRegistByMobile = 1;
                            jsRegistFed.showMobileValidCode()
                        }
                    } else {
                        if (c.errorCode == 15) {
                            showPhoneError("格式错误，请输入正确的手机号码")
                        } else {
                            if (c.errorCode == 16) {
                                showPhoneError("该手机号已存在，<a href='/passport/login_input.do'>登录</a>")
                            } else {
                                if (c.errorCode == 17) {
                                    alert("每天只有3次获取验证码的机会")
                                } else {
                                    a = true
                                }
                            }
                        }
                    }
                } if (a && undefined != b && null != b) {
                    b.call();
                    jsRegistFed.reSetValidCodeFlage = true
                }
            }
        })
    },
    mobileRegist: function() {
        $(".mobile_register_form .recv_mobile_code").delegate(".receive_code", "click", function(a) {
            if (!$(".receive_code", ".mobile_register_form .recv_mobile_code").hasClass("reacquire_code")) {
                if (showValidCodeWhenRegistByMobile == 1) {
                    jsRegistFed.showMobileValidCode()
                } else {
                    jsRegistFed.showMobileRecvCode();
                    jsRegistFed.getMobileRecvCode()
                }
            }
        })
    },
    showMobileValidCode: function() {
        $("#mobile_validcode_error").removeClass("regist_tips_error");
        $("#mobile_validcode_error").find("p").text("");
        $(".mb_code_box").animate({
            left: jsRegistFed.mobileValidCodeLeft
        }, 300, function() {
            refresh_valid_code(window, mobile_captcha_callback)
        })
    },
    showMobileRecvCode: function() {
        $("#mobile_validcode_error").removeClass("regist_tips_error");
        $("#mobile_validcode_error").find("p").text("");
        if (showValidCodeWhenRegistByMobile == 1) {
            $(".mb_code_box").animate({
                left: jsRegistFed.mobileRecvCodeLeft
            }, 300, function() {
                jsRegistFed.mobileRecvCodeCountdown()
            })
        } else {
            jsRegistFed.mobileRecvCodeCountdown()
        }
    },
    mobileRecvCodeCountingdown: false,
    mobileRecvCodeCountingdownAutotime: null,
    mobileRecvCodeCountingdownTimeout: null,
    mobileRecvCodeCountdown: function() {
        jsRegistFed.mobileRecvCodeCountingdown = true;
        $(".regist_form .recv_mobile_code a.receive_code").addClass("reacquire_code").html("重新获取验证码(<i>59</i>)");
        var a = $("i", ".mobile_register_form .recv_mobile_code .reacquire_code").text();
        jsRegistFed.mobileRecvCodeCountingdownAutotime = setInterval(function() {
            if (a > 0) {
                a--;
                $("i", ".mobile_register_form .recv_mobile_code .reacquire_code").text(a)
            } else {
                clearInterval(jsRegistFed.mobileRecvCodeCountingdownAutotime);
                jsRegistFed.mobileRecvCodeCountingdownAutotime = null
            }
        }, 1000);
        jsRegistFed.mobileRecvCodeCountingdownTimeout = setTimeout(function() {
            $(".receive_code", ".mobile_register_form .recv_mobile_code").removeClass("reacquire_code").html("重新获取验证码");
            jsRegistFed.mobileRecvCodeCountingdown = false
        }, a * 1000);
        return false
    },
    initMobileRegist: function() {
        $(".regist_form .recv_mobile_code .check_code").hover(function() {
            $(this).find("i").show()
        }, function() {
            $(this).find("i").hide()
        });
        $("#phone").change(function() {
            jQuery("#m_code_right").hide();
            jQuery("#m_code_wrong").hide();
            if (jsRegistFed.reSetValidCodeFlage) {
                $("#validPhoneCode").val("6位验证码");
                jsRegistFed.showMobileValidCode();
                jsRegistFed.reSetValidCodeFlage = false
            }
            if (null != jsRegistFed.mobileRecvCodeCountingdownAutotime) {
                clearInterval(jsRegistFed.mobileRecvCodeCountingdownAutotime);
                jsRegistFed.mobileRecvCodeCountingdownAutotime = null;
                jsRegistFed.mobileRecvCodeCountingdown = false
            }
            if (null != jsRegistFed.mobileRecvCodeCountingdownTimeout) {
                clearTimeout(jsRegistFed.mobileRecvCodeCountingdownTimeout);
                jsRegistFed.mobileRecvCodeCountingdownTimeout = null
            }
            $("#phone_desc").css("display", "none");
            $(".receive_code", ".recv_mobile_code").html("获取验证码");
            var b = $(this).val();
            if (b == "" || b == "请输入手机号码") {
                showPhoneError("手机号码不能为空");
                return false
            }
            var a = /^(13|15|18|14|17)[0-9]{9}$/;
            if (!a.test(b)) {
                showPhoneError("格式错误，请输入正确的手机号码");
                return false
            }
            $.ajax({
                type: "POST",
                url: "/check/check_phone.do",
                data: {
                    validPhone: $("#phone").val()
                },
                success: function(c) {
                    if (c.checkResult == 0) {
                        jQuery("#phone_tip").hide();
                        $("#phone_desc").css("display", "block");
                        jQuery("#phone").parents("li").removeClass("cur_error");
                        var d = jQuery("#validCodeMobile");
                        d.removeAttr("readonly");
                        d.css("background", "")
                    } else {
                        if (c.checkResult == 1) {
                            showPhoneError("该手机号已存在，<a href='/passport/login_input.do'>登录</a>")
                        }
                    }
                }
            })
        })
    },
    registSucceed: function(f) {
        var e = $("#regist_popWin");
        e = '<div class="regist_success regist_popWin">    <div class="regist_popWin_con">        <div class="regist_popWin_title">            <a href="javascript:void(0)" class="regist_popWin_closeBtn"></a>        </div>        <div class="regist_popWin_Info clearfix">            <div class="regist_popWin_main">                <div class="regist_popWin_mainCon">                    <p class="tit"><i></i>注册成功</p>                </div>            </div>        </div>        <div class="popWin_tips">            <span>3</span>s后跳转至首页        </div>    </div></div>';
        var d = $.layer({
            type: 1,
            title: false,
            area: ["auto", "auto"],
            border: [0],
            shade: [0.5, "#000"],
            closeBtn: [0, false],
            page: {
                html: e
            }
        });
        $(".regist_popWin_closeBtn").on("click", function() {
            window.location.href = f
        });
        var c = $(".popWin_tips span", ".regist_success ").text();
        var a = setInterval(function() {
            if (c > 0) {
                c--;
                $(".popWin_tips span", ".regist_success ").text(c)
            }
        }, 1000);
        var b = setTimeout(function() {
            window.location.href = f
        }, 3000)
    },
    phoneCode: function() {
        $(".ipt", ".img_code").on("keyup", function() {
            var a = $(this).val();
            register_param_validate(a, check_success, check_failure)
        })
    },
    showMailForm: function() {
        $(".joint_landing").delegate(".validate_mail", "click", function() {
            $(this).toggleClass("show_mail_form");
            $(".mail_verifica").toggle()
        })
    },
    receivePhoneCode: function() {
        $(".joint_landing").delegate(".receive_code", "click", send_mobile_captcha)
    },
    loadFunRegist: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.registTab();
        jsRegistFed.registForm(".ipt_username");
        jsRegistFed.registForm(".ipt_phone");
        jsRegistFed.registForm(".ipt_code");
        jsRegistFed.registForm(".ipt_phonecode");
        jsRegistFed.serviceAgreement();
        jsRegistFed.rate(0, "arrow_email");
        jsRegistFed.rate(0, "arrow_mobile");
        jsRegistFed.paswdStrength("#password_email");
        jsRegistFed.paswdStrength("#password_mobile");
        jsRegistFed.mobileRegist();
        jsRegistFed.initMobileRegist();
        jsRegistFed.registForm(".phone_num");
        jsRegistFed.phoneCode()
    },
    loadFunEnglishRegist: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.registForm(".ipt_username");
        jsRegistFed.registForm(".ipt_code");
        jsRegistFed.serviceAgreement();
        jsRegistFed.rate(0, "arrow_email");
        jsRegistFed.paswdStrength("#password_email")
    },
    loadFunRegistSuccess: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.changeNickName();
        jsRegistFed.emailReceive();
        jsRegistFed.paswdStrength("#password");
        jsRegistFed.successRotate()
    },
    loadFunFindPassword: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.registForm(".ipt_username");
        jsRegistFed.registForm(".ipt_code");
        jsRegistFed.receiveCode()
    },
    loadFunFindPassword2: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.registForm(".ipt_username");
        jsRegistFed.registForm(".ipt_code");
        jsRegistFed.receiveCode();
        jsRegistFed.rate(0, "arrow");
        jsRegistFed.paswdStrength("#password")
    },
    loadFunJointLanding: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.registForm(".ipt_username");
        jsRegistFed.rate(0, "arrow");
        jsRegistFed.paswdStrength("#password")
    },
    loadFunBtbRegist: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.registForm(".ipt_username");
        jsRegistFed.registForm(".ipt_companyName");
        jsRegistFed.registForm(".ipt_linkmanMobile");
        jsRegistFed.registForm(".ipt_landLine");
        jsRegistFed.registForm(".ipt_address1");
        jsRegistFed.registForm(".ipt_linkmanName");
        jsRegistFed.registForm(".ipt_validCode");
        jsRegistFed.rate(0, "arrow");
        jsRegistFed.paswdStrength("#password");
        jsRegistFed.areaSelect();
        jsRegistFed.serviceAgreement()
    },
    loadFunPopwin: function(a) {
        jsRegistFed.registSucceed(a)
    },
    loadFunPhoneLanding: function() {
        jsRegistFed.helpCenterHover();
        jsRegistFed.registForm(".ipt_username");
        jsRegistFed.registForm(".ipt_code");
        jsRegistFed.showMailForm();
        jsRegistFed.receivePhoneCode();
        UnionLoginForNewUser.phoneCode();
        jsRegistFed.rate(0, "arrow");
        UnionLoginForNewUser.paswdStrength("#password")
    }
};

function checkValidCodeOnBlur() {
    var a = jQuery("#validCode").val();
    if (a == "") {
        jQuery("#code_right").hide();
        jQuery("#code_wrong").show();
        jQuery("#validCode").parents("li").addClass("cur_error")
    }
}

function Timer() {
    this.timer = null;
    this.startInterval = function(b, a) {
        var c = function() {};
        if (typeof a == "function") {
            c = a
        }
        this.timer = setInterval(c, 1000)
    };
    this.start = function(b, a) {
        var c = function() {};
        if (typeof a == "function") {
            c = a
        }
        this.timer = setTimeout(c, 60 * 1000)
    };
    this.stop = function() {
        if (this.timer != null) {
            this.timer = null;
            clearInterval(this.timer)
        }
    }
}
var timer1 = null;
var timer2 = null;
on_send_mobile_captcha_success = function(a) {
    if (!$(".phone_code").is(":hidden")) {
        $(".email_register_form .phone_code .receive_code").addClass("reacquire_code").html("重新获取验证码(<i>59</i>)");
        var b = $("i", ".email_register_form .phone_code .reacquire_code").text();
        var d = function() {
            if (b > 0) {
                b--;
                $(".receive_code i", ".email_register_form .phone_code").text(b)
            } else {}
        };
        timer1 = new Timer();
        timer1.startInterval({}, d);
        var c = function() {
            $(".receive_code", ".phone_code").removeClass("reacquire_code").html("<span>重新获取验证码</span>")
        };
        timer2 = new Timer();
        timer2.start({}, c)
    }
};
on_send_mobile_captcha_fail = function(c) {
    $(".phone_code").hide();
    $(".email_register_form .img_code").show();
    $(".email_register_form .img_code .code_right").hide();
    $(".email_register_form .img_code .code_wrong").show();
    refresh_valid_code(window, email_captcha_callback);
    if (c == 1) {
        var b = $(".tips");
        var a = new Tips(b, "参数不对");
        a.show();
        return
    } else {
        if (c == 15) {
            var b = $(".tips");
            var a = new Tips(b, "手机号码不正确");
            a.show();
            return
        } else {
            if (c == 16) {
                var b = $(".tips");
                var a = new Tips(b, "手机号码已注册");
                a.show();
                return
            } else {
                if (c == 20) {
                    var b = $(".tips");
                    var a = new Tips(b, "校验失败");
                    a.show();
                    return
                } else {
                    if (c == 17) {
                        var b = $(".tips");
                        var a = new Tips(b, "手机验证码发送超过次数3次");
                        a.show();
                        return
                    }
                }
            }
        }
    }
    var b = $(".tips");
    var a = new Tips(b, "短信发送失败");
    a.show()
};

function check_failure() {
    $(".email_register_form .img_code .code_right").hide();
    $(".email_register_form .img_code .code_wrong").show()
}

function check_success() {
    $(".email_register_form .img_code .code_right").show();
    $(".email_register_form .img_code .code_wrong").hide();
    $(".email_register_form .img_code").hide();
    $(".phone_code").show();
    Captcha.sendMobileCaptchaWithParam(Captcha.url4, {
        validCode: $(".email_register_form .img_code .ipt_code").val(),
        sig: $("#emailValidateSig").val(),
        "user.cellphone": $(".phone_num").val()
    }, on_send_mobile_captcha_success, on_send_mobile_captcha_fail)
}

function register_param_validate(c, d, a) {
    if (c == "") {
        a.apply(window);
        return false
    }
    if (c.length != 4) {
        a.apply(window);
        return false
    }
    var e = {
        validCode: c,
        sig: jQuery("#emailValidateSig").val()
    };
    var b = URLPrefix.passport + "/passport/register_param_validate.do";
    jQuery.post(b, e, function(f) {
        if (f) {
            if (f.errorCode != 0) {
                if (f.errorCode == 2) {
                    a.apply(window);
                    if (f.refresh) {
                        if (f.refresh == 1) {
                            refresh_valid_code(window, email_captcha_callback)
                        }
                    }
                }
            } else {
                d.apply(window)
            }
        } else {
            a.apply(window)
        }
    })
}

function checkRegisterParamForMobile() {
    var c = jQuery("#validCodeMobile");
    var b = c.val();
    var d = jQuery("#phone_desc").css("display");
    if (d != "block") {
        return
    }
    if (b.length < 4) {
        return
    }
    var e = {
        validCode: b,
        sig: jQuery("#validateSig").val()
    };
    var a = URLPrefix.passport + "/passport/register_param_validate.do";
    jQuery.post(a, e, function(f) {
        if (f) {
            if (f.errorCode != 0) {
                if (f.errorCode == 2) {
                    jQuery("#m_code_right").hide();
                    jQuery("#m_code_wrong").show();
                    c.parents("li").addClass("cur_error");
                    if (f.refresh) {
                        if (f.refresh == 1) {
                            refresh_valid_code(window, mobile_captcha_callback)
                        }
                    }
                }
            } else {
                jQuery("#m_code_right").show();
                jQuery("#m_code_wrong").hide();
                c.parents("li").removeClass("cur_error");
                jsRegistFed.getMobileRecvCode(function() {
                    jsRegistFed.showMobileRecvCode()
                });
                jQuery("#validPhoneCode").focus()
            }
        } else {
            jQuery("#m_code_right").hide();
            jQuery("#m_code_wrong").show();
            c.parents("li").addClass("cur_error")
        }
    })
}

function checkValidCodeOnFocusForMobileRegister() {
    var a = jQuery("#validCodeMobile");
    a.val("");
    var b = jQuery("#phone_desc").css("display");
    if (b != "block") {
        a.attr("readonly", "readonly");
        a.css("background", "#cccccc")
    } else {
        a.removeAttr("readonly");
        a.css("background", "")
    }
}

function checkValidCodeOnBlurForMobileRegister() {
    var a = jQuery("#validCodeMobile").val();
    if (a == "") {
        jQuery("#m_code_right").hide();
        jQuery("#m_code_wrong").show();
        jQuery("#validCodeMobile").parents("li").addClass("cur_error")
    }
    var b = jQuery("#validCodeMobile");
    b.removeAttr("readonly");
    b.css("background", "")
}

function registerSubmit() {
    if (!doSubmit("password_email")) {
        refresh_valid_code(window, email_captcha_callback);
        return
    }
    var e = $(".ipt_code", ".email_register_form .phone_code").val();
    if (e == "" || e.length != 6) {
        $(".email_register_form .phone_code .code_right").hide();
        $(".email_register_form .phone_code .code_wrong").show();
        return
    }
    var h = $("#email").val();
    var i = $("#password_email").val();
    var d = $("#password_email2").val();
    var g = new JSEncrypt();
    var f = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDXQG8rnxhslm+2f7Epu3bB0inrnCaTHhUQCYE+2X+qWQgcpn+Hvwyks3A67mvkIcyvV0ED3HFDf+ANoMWV1Ex56dKqOmSUmjrk7s5cjQeiIsxX7Q3hSzO61/kLpKNH+NE6iAPpm96Fg15rCjbm+5rR96DhLNG7zt2JgOd2o1wXkQIDAQAB";
    g.setPublicKey(f);
    h = g.encrypt(h);
    i = g.encrypt(i);
    d = g.encrypt(d);
    var b = $(".phone_num").val();
    if (!check_mobile($(".phone_num"))) {
        return
    }
    b = g.encrypt(b);
    var c = {
        "user.email": h,
        "user.password": i,
        password2: d,
        validPhone: b,
        validCode: e,
        sig: jQuery("#emailValidateSig").val(),
        returnUrl: $("#returnUrl").val(),
        activities: $("#activities").val(),
        lockEmail: $("#lockEmail").val()
    };
    var a = URLPrefix.passport + "/passport/registerByEmail.do";
    jQuery.post(a, c, function(j) {
        if (j.errorCode == 0 || j.errorCode == 14) {
            jsRegistFed.loadFunPopwin(j.returnUrl)
        } else {
            refresh_valid_code(window, email_captcha_callback);
            if (j.errorCode == 1) {
                showEmailError("不能为空");
                return
            } else {
                if (j.errorCode == 2) {
                    $(".phone_code").hide();
                    $(".email_register_form .img_code").show();
                    refresh_valid_code(window, email_captcha_callback);
                    $(".email_register_form .img_code .ipt_code").val("");
                    $(".email_register_form .phone_code .ipt_code").val("");
                    $(".email_register_form .img_code .code_right").hide();
                    $(".email_register_form .img_code .code_wrong").show();
                    return
                } else {
                    if (j.errorCode == 15) {
                        $(".regist_tips_error", ".email_register_form .phone_num_wrap").html("<u></u>&nbsp;手机号码格式不正确");
                        $(".regist_tips_error", ".email_register_form .phone_num_wrap").show();
                        $(".phone_num_wrap").addClass("cur_error");
                        return
                    } else {
                        if (j.errorCode == 16) {
                            $(".regist_tips_error", ".email_register_form .phone_num_wrap").html("<u></u>&nbsp;手机号码已绑定");
                            $(".regist_tips_error", ".email_register_form .phone_num_wrap").show();
                            $(".phone_num_wrap").addClass("cur_error");
                            return
                        } else {
                            if (j.errorCode == 3) {
                                showEmailError("格式错误，请输入正确的邮箱");
                                return
                            } else {
                                if (j.errorCode == 18) {
                                    showEmailError("该邮箱已存在，<a href='/passport/login_input.do'>登录</a>");
                                    return
                                } else {
                                    if (j.errorCode == 4) {
                                        showPass2Error("password_email", "两次密码输入不一致");
                                        return
                                    } else {
                                        if (j.errorCode == 5) {
                                            showPassError("password_email", "您的密码存在安全隐患,请更改 ");
                                            return
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            alert("注册失败")
        }
    })
}

function registerByPhoneSubmit() {
    if (!doPhoneSubmit("password_mobile")) {
        return
    }
    var h = jQuery("#validPhoneCode").val();
    var d = "";
    if ("" == h || "6位验证码" == h) {
        d = "请输入6位短信验证码"
    } else {
        if (h.length != 6) {
            d = "短信验证码错误"
        }
    } if (d != "") {
        $("#validPhoneCode_wrong").show();
        jQuery("#validPhoneCode_wrong").parents("li").addClass("cur_error");
        $(".regist_form .recv_mobile_code").addClass("cur_error");
        $("#mobile_validcode_error").addClass("regist_tips_error");
        $("#mobile_validcode_error").find("p").text(d);
        return
    }
    var g = $("#phone").val();
    var i = $("#password_mobile").val();
    var c = $("#password_mobile2").val();
    var f = new JSEncrypt();
    var e = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDXQG8rnxhslm+2f7Epu3bB0inrnCaTHhUQCYE+2X+qWQgcpn+Hvwyks3A67mvkIcyvV0ED3HFDf+ANoMWV1Ex56dKqOmSUmjrk7s5cjQeiIsxX7Q3hSzO61/kLpKNH+NE6iAPpm96Fg15rCjbm+5rR96DhLNG7zt2JgOd2o1wXkQIDAQAB";
    f.setPublicKey(e);
    g = f.encrypt(g);
    i = f.encrypt(i);
    c = f.encrypt(c);
    var b = {
        validPhone: g,
        "user.password": i,
        password2: c,
        validCode: $("#validPhoneCode").val(),
        returnUrl: $("#returnUrl").val(),
        activities: $("#activities").val(),
        lockEmail: $("#lockEmail").val()
    };
    var a = URLPrefix.passport + "/passport/registerByPhone.do";
    jQuery.post(a, b, function(j) {
        if (j.errorCode == 0 || j.errorCode == 14) {
            jsRegistFed.loadFunPopwin(j.returnUrl)
        } else {
            if (j.errorCode == 1) {
                showPhoneError("不能为空")
            } else {
                if (j.errorCode == 2) {
                    $("#validPhoneCode_wrong").show();
                    jQuery("#validPhoneCode_wrong").parents("li").addClass("cur_error");
                    $(".regist_form .recv_mobile_code").addClass("cur_error");
                    $("#mobile_validcode_error").addClass("regist_tips_error");
                    $("#mobile_validcode_error").find("p").text("验证码错误")
                } else {
                    if (j.errorCode == 15) {
                        showPhoneError("格式错误，请输入正确的手机号")
                    } else {
                        if (j.errorCode == 16) {
                            showPhoneError("该手机号已存在，<a href='/passport/login_input.do'>登录</a>")
                        } else {
                            if (j.errorCode == 4) {
                                showPass2Error("password_mobile", "两次密码输入不一致")
                            } else {
                                if (j.errorCode == 5) {
                                    showPassError("password_mobile", "您的密码存在安全隐患,请更改 ")
                                } else {
                                    if (j.errorCode == 13) {
                                        alert("系统异常")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    })
}

function checkEmailAfterRegister(a) {
    if (a == "") {
        alert("请登录您的邮箱去验证哦~~~")
    } else {
        window.location.href = a
    }
}

function reSendEamil() {
    $.ajax({
        type: "POST",
        url: "/passport/sendRegisterMail.do",
        success: function(a) {
            if (a == 1) {
                alert("邮件已重新发送至您的邮箱，请查收！");
                return false
            } else {
                if (a == 2) {
                    alert("重新发送邮件失败，请稍后再试");
                    return false
                } else {
                    if (a == 3) {
                        alert("在24小时内不可以重发邮件超过3次");
                        return false
                    }
                }
            }
        }
    })
}

function loadImageUrl(b, a) {
    var c = {
        adCode: b
    };
    var d = "/passport/loadAd.do";
    jQuery.post(d, c, function(e) {
        if (e) {
            if (e.imageUrl) {
                $("#imgLink").show();
                $("#img").attr("src", e.imageUrl);
                if (e.linkUrl) {
                    $("#imgLink").attr("href", e.linkUrl);
                    $("#imgLink").click(function() {
                        addTrackPositionToCookie("1", a)
                    })
                }
            }
        }
    })
}

function check_mobile(c) {
    var a = c.val();
    if (a == "" || a == c.context.defaultValue) {
        $(".regist_tips_error", ".email_register_form .phone_num_wrap").html("<u></u>&nbsp;不能为空");
        $(".regist_tips_error", ".email_register_form .phone_num_wrap").show();
        $(".phone_num_wrap").addClass("cur_error");
        return false
    }
    var b = /^1\d{10}$/;
    if (!b.test(a)) {
        $(".regist_tips_error", ".email_register_form .phone_num_wrap").html("<u></u>&nbsp;格式错误，请输入正确的手机号码");
        $(".regist_tips_error", ".email_register_form .phone_num_wrap").show();
        $(".phone_num_wrap").addClass("cur_error");
        return false
    }
    $(".regist_tips_error", ".email_register_form .phone_num_wrap").html("");
    $(".regist_tips_error", ".email_register_form .phone_num_wrap").hide();
    $(".phone_num_wrap").removeClass("cur_error");
    return true
}

function bindEvent() {
    $("#password_email").on("focus", function() {
        var c = "password_email";
        var b = jQuery("#" + c);
        if (b.val() == "") {
            hideOtherTips(c);
            return
        }
        checkPassWordContent(c);
        hideOtherTips(c + "2");
        showoff(c + "2_desc")
    });
    $("#password_email").on("blur", function() {
        var b = "password_email";
        hideOtherTips(b);
        var c = check_pwd1(b);
        if (c != 0) {
            jQuery("#" + b + "2").attr("readonly", "readonly")
        }
        if (c == 1) {
            showPassError(b, "密码不能为空")
        } else {
            if (c == 2) {
                showPassError(b, "密码为6-20位字符")
            } else {
                if (c == 3) {
                    showPassError(b, "密码为6-20位字符")
                } else {
                    if (c == 4) {
                        showPassError(b, "密码中不允许有空格")
                    } else {
                        if (c == 5) {
                            showPassError(b, "密码不能全为数字")
                        } else {
                            if (c == 6) {
                                showPassError(b, "密码不能全为字母，请包含至少1个数字或符号 ")
                            } else {
                                if (c == 7) {
                                    showPassError(b, "密码不能全为符号")
                                } else {
                                    if (c == 8) {
                                        showPassError(b, "密码不能全为相同字符或数字")
                                    } else {
                                        var d;
                                        if (b.indexOf("phone") > -1) {
                                            d = {
                                                userName: $("#phone").val(),
                                                password: $("#" + b).val()
                                            }
                                        } else {
                                            d = {
                                                userEmail: $("#email").val(),
                                                password: $("#" + b).val()
                                            }
                                        }
                                        jQuery.ajax({
                                            type: "POST",
                                            url: "/check/check_unsafeInfo.do",
                                            data: d,
                                            success: function(e) {
                                                switch (e.checkResult) {
                                                    case 1:
                                                        showPassError(b, "您的密码存在安全隐患,请更改 ");
                                                        break;
                                                    case 0:
                                                        jQuery("#" + b + "2").removeAttr("readonly");
                                                        break;
                                                    default:
                                                        jQuery("#" + b + "2").removeAttr("readonly");
                                                        break
                                                }
                                            }
                                        })
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    });
    var a = true;
    $(".phone_num").change(function() {
        if (timer1 != null) {
            timer1.stop()
        }
        if (timer2 != null) {
            timer2.stop()
        }
        $(".phone_code").hide();
        $(".email_register_form .img_code").show();
        if (!a) {
            refresh_valid_code(window, email_captcha_callback)
        }
        $(".email_register_form .img_code .ipt_code").val("");
        $(".email_register_form .phone_code .ipt_code").val("");
        $(".email_register_form .img_code .code_right").hide();
        $(".email_register_form .img_code .code_wrong").show();
        a = false
    });
    $(".phone_num").bind("blur", function() {
        var b = $(this).val();
        if (!check_mobile($(this))) {
            return
        }
        $.ajax({
            type: "POST",
            url: "/check/check_phone.do",
            data: {
                validPhone: b
            },
            success: function(c) {
                if (c.checkResult == 0) {
                    $(".regist_tips_error", ".email_register_form .phone_num_wrap").html("");
                    $(".regist_tips_error", ".email_register_form .phone_num_wrap").hide();
                    $(".phone_num_wrap").removeClass("cur_error")
                } else {
                    if (c.checkResult == 1) {
                        $(".regist_tips_error", ".email_register_form .phone_num_wrap").html("<u></u>该手机号已存在，<a href='/passport/login_input.do' class='blue_link'>登录</a>");
                        $(".regist_tips_error", ".email_register_form .phone_num_wrap").show();
                        $(".phone_num_wrap").addClass("cur_error")
                    }
                }
            }
        })
    });
    $(".ipt_code", ".email_register_form .phone_code").bind("blur", function() {
        var b = $(this).val();
        if (b == "") {
            $(".email_register_form .phone_code .code_right").hide();
            $(".email_register_form .phone_code .code_wrong").show();
            return false
        }
        if (b.length != 6) {
            $(".email_register_form .phone_code .code_right").hide();
            $(".email_register_form .phone_code .code_wrong").show();
            return false
        }
        $(".email_register_form .phone_code .code_right").show();
        $(".email_register_form .phone_code .code_wrong").hide()
    });
    $(".email_register_form").delegate(".receive_code", "click", function() {
        if (!$(".email_register_form .receive_code").hasClass("reacquire_code")) {
            $(".phone_code").hide();
            $(".email_register_form .img_code").show();
            refresh_valid_code(window, email_captcha_callback);
            $(".email_register_form .img_code .ipt_code").val("");
            $(".email_register_form .phone_code .ipt_code").val("");
            $(".email_register_form .img_code .code_right").hide();
            $(".email_register_form .img_code .code_wrong").show()
        }
    });
    $(".email_register_form").delegate(".img_code .change_code", "click", function() {
        refresh_valid_code(window, email_captcha_callback)
    });
    $(".email_register_form").delegate(".img_code img", "click", function() {
        refresh_valid_code(window, email_captcha_callback)
    })
}

function email_captcha_callback(a) {
    var c = $(".email_register_form .img_code img");
    jQuery("#emailValidateSig").val(a);
    var b = "https://captcha.yhd.com/public/getjpg.do?sig=" + a;
    c.attr("src", b)
}

function mobile_captcha_callback(a) {
    var c = $(".mobile_register_form .recv_mobile_code img");
    jQuery("#validateSig").val(a);
    var b = "https://captcha.yhd.com/public/getjpg.do?sig=" + a;
    c.attr("src", b)
};
Captcha = {
    mode: "remote",
    url0: "/passport/valid_code.do",
    url: "https://captcha.yhd.com/public/getjpg.do",
    getCaptchaUrl: function() {
        if (this.mode == "remote") {
            return this.url
        }
        return this.url0
    },
    load: function(a) {
        var b = $(a);
        var c = this.getCaptchaUrl();
        if (b) {
            if (c == this.url) {
                getValidateSigAndSetImageSrc(b)
            } else {
                b.attr("src", c + "?t=" + Math.random())
            }
        }
    },
    url2: "/m/mSendCheckCodeForRegister.do",
    url3: "/passport/sendMobileCheckCode.do",
    url4: "/passport/sendCheckCodeForRegister.do",
    url5: "/validator/send.do",
    sendMobileCaptchaWithParam: function(b, d, c, a) {
        $.ajax({
            type: "POST",
            url: b,
            data: d,
            async: false,
            success: function(e) {
                if (e) {
                    if (e.errorCode != 0) {
                        var f = e.errorCode;
                        a.apply(this, [f]);
                        return
                    }
                    c.apply(this, [e.errorCode])
                }
            }
        })
    },
    sendMobileCaptcha: function(c, a, d, b) {
        $.ajax({
            type: "POST",
            url: c,
            async: false,
            success: function(e) {
                if (e) {
                    if (e.errorCode != 0) {
                        var f = e.errorCode;
                        b.apply(this, [f]);
                        return
                    }
                    d.apply(this, [e.errorCode])
                }
            }
        })
    },
    send: function(c, b, a) {
        this.sendMobileCaptcha(this.url2, c, b, a)
    },
    setMode: function(a) {
        this.mode = a
    }
};

function getValidateSigAndSetImageSrc(a) {
    $.ajax({
        type: "GET",
        dataType: "jsonp",
        jsonp: "callback",
        jsonpCallback: "callback123",
        url: "https://captcha.yhd.com/public/getsig.do?t=" + Math.random(),
        success: function(b) {
            var d = b.sig;
            $("#validateSig").val(d);
            var c = "https://captcha.yhd.com/public/getjpg.do?sig=" + d;
            a.attr("src", c)
        }
    })
}
ValidatorProvider = {};
ValidatorProvider.onBlur = function(b, c) {
    var a = jQuery("#vcd").val();
    if (a == "") {
        jQuery("#code_right").hide();
        jQuery("#code_wrong").show();
        showErrorInfo($("#vcd"), "请输入校验码")
    } else {
        if (a.length != 4) {
            jQuery("#code_right").hide();
            jQuery("#code_wrong").show();
            showErrorInfo($("#vcd"), "验证码格式不正确")
        }
    }
};
ValidatorProvider.setValidateUrl = function(a) {
    this["validateUrl"] = a
};
ValidatorProvider.success = function(a) {};
ValidatorProvider.fail = function(a) {};
ValidatorProvider.error = function(a) {};
ValidatorProvider.onValidate = function() {
    var b = $("#vcd").val();
    if (b.length != 4) {
        jQuery("#code_right").hide();
        jQuery("#code_wrong").show();
        showErrorInfo($("#vcd"), "验证码格式不正确");
        return
    }
    var a = {
        validCode: b
    };
    jQuery.post(this["validateUrl"], a, function(c) {
        if (c) {
            if (c.errorCode != 0) {
                if (c.errorCode == 2) {
                    jQuery("#code_right").hide();
                    jQuery("#code_wrong").show();
                    if (c.refresh) {
                        if (c.refresh == 1) {
                            passport_refresh_valid_code()
                        }
                    }
                    showErrorInfo($("#vcd"), "验证码错误，请重新输入")
                }
            } else {
                jQuery("#code_right").show();
                jQuery("#code_wrong").hide();
                clearErrorInfo()
            }
        } else {
            jQuery("#code_right").hide();
            jQuery("#code_wrong").show();
            clearErrorInfo()
        }
    })
};
Validator = {};
Validator.registerValidatorProvider = function() {};
var commonSymbol = "[\\,\\`\\~\\!\\@\\#\\$\\%\\\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\{\\]\\}\\\\|\\;\\:\\‘\\’\\“\\”\\<\\>\\/?]+";
var spliter = ",";

function showoff(c) {
    var d = c.split("_");
    if (d[0] != "password") {
        jQuery("#" + d[0] + "_error").hide();
        jQuery("#" + d[0] + "_tip").show()
    }
    jQuery("#" + c + "").hide()
}

function trim(b) {
    return b.replace(/(^\s*)|(\s*$)/g, "")
}

function ltrim(b) {
    return b.replace(/(^\s*)/g, "")
}

function rtrim(b) {
    return b.replace(/(\s*$)/g, "")
}

function isSameWord(g) {
    var e;
    if (g != null && g != "") {
        e = g.charCodeAt(0);
        e = "\\" + e.toString(8);
        var f = "[" + e + "]{" + (g.length) + "}";
        var h = new RegExp(f);
        return h.test(g)
    }
    return true
}

function hideOtherTips(b) {
    if (jQuery("#" + b + "").val() == "") {
        jQuery("#" + b + "_error").hide();
        jQuery("#" + b + "_tip").show()
    }
    jQuery("#" + b + "").parents("li").removeClass("cur_error")
}

function check_email() {
    var d = jQuery("#email").val();
    if (d == "") {
        return 1
    }
    var c = /^\w[\w\$\^\(\)\[\]\{\}\.\-\+,]{0,100}@([a-zA-Z0-9][\w\-]*\.)+[a-zA-Z]{2,6}$/;
    if (!c.test(d)) {
        return 2
    }
    if (d.length > 100) {
        return 3
    }
    if ((/@yahoo.cn$\b/).test(d.toLowerCase()) || (/@yahoo.com.cn$\b/).test(d.toLowerCase())) {
        return 4
    }
    return 0
}

function check_phone() {
    var d = jQuery("#phone").val();
    if (d == "" || d == "请输入手机号码") {
        return 1
    }
    var c = /^1\d{10}$/;
    if (!c.test(d)) {
        return 2
    }
    return 0
}

function check_pwd1(l) {
    var k = $("#" + l).val();
    if (k == "") {
        return 1
    }
    if (k.length > 20) {
        return 2
    }
    if (k.length < 6) {
        return 3
    }
    var m = /\s+/;
    if (m.test(k)) {
        return 4
    }
    var r = /^[0-9]+$/;
    if (r.test(k)) {
        return 5
    }
    var q = /^[a-zA-Z]+$/;
    if (q.test(k)) {
        return 6
    }
    var j = /^[^0-9A-Za-z]+$/;
    if (j.test(k)) {
        return 7
    }
    if (isSameWord(k)) {
        return 8
    }
    var n = "d*" + commonSymbol + "";
    var o = "\\\d+[A-Za-z]|[A-Za-z]+[0-9]+|[A-Za-z]+" + commonSymbol + "[0-9]+|[A-Za-z]+[0-9]+" + commonSymbol + "|" + n + "";
    var p = new RegExp(o);
    if (!p.test(k)) {
        return 10
    }
    return 0
}

function check_pwd2(e) {
    var f = $("#" + e).val();
    var d = $("#" + e + "2").val();
    if (d == "") {
        return 1
    }
    if (f != d) {
        return 2
    }
    return 0
}

function check_referer() {
    var b = $("#referer").val().replace(/(^ *)|( *$)/g, "");
    if (b != "") {
        if ($("#refererDesc").html().indexOf("image") == -1) {
            return 1
        }
    }
}

function showErrorInfo(d, e) {
    jQuery("#" + d + "").html("<u></u>" + e + "").show();
    jQuery("#" + d + "").parents("li").addClass("cur_error");
    var f = d.split("_");
    jQuery("#" + f[0] + "_desc").hide()
}

function showPassError(d, c) {
    jQuery("#" + d + "_tip").hide();
    jQuery("#" + d + "_Level").hide();
    showErrorInfo(d + "_error", c)
}

function showPass2Error(d, c) {
    jQuery("#" + d + "2_tip").hide();
    showErrorInfo(d + "2_error", c)
}

function showEmailError(b) {
    jQuery("#email_tip").hide();
    showErrorInfo("email_error", b)
}

function showPhoneError(b) {
    jQuery("#phone_tip").hide();
    showErrorInfo("phone_error", b)
}

function checkCodeOnBlur(c) {
    var d = jQuery("#" + c).val();
    if (d == "" || d.length != 4) {
        $("#" + c + "_wrong").show();
        jQuery("#" + c + "_wrong").parents("li").addClass("cur_error")
    }
}

function checkEmailOnBlur() {
    var b = check_email();
    if (b == 1) {
        showEmailError("不能为空")
    } else {
        if (b == 2) {
            showEmailError("格式错误，请输入正确的邮箱")
        } else {
            if (b == 3) {
                showEmailError("邮箱长度不能超过100位字符")
            } else {
                if (b == 4) {
                    showEmailError("雅虎中国邮箱已停用，请更换邮箱注册")
                } else {
                    $.ajax({
                        type: "POST",
                        url: "/check/check_email.do",
                        data: {
                            userEmail: $("#email").val()
                        },
                        success: function(a) {
                            if (a.checkResult == 0) {
                                jQuery("#email_tip").hide();
                                $("#email_desc").css("display", "block");
                                jQuery("#email").parents("li").removeClass("cur_error")
                            } else {
                                if (a.checkResult == 1) {
                                    showEmailError("该邮箱已存在，<a href='/passport/login_input.do'>登录</a>")
                                }
                            }
                        }
                    })
                }
            }
        }
    }
}

function checkPhoneOnBlur() {
    var b = check_phone();
    if (b == 1) {
        showPhoneError("不能为空")
    } else {
        if (b == 2) {
            showPhoneError("格式错误，请输入正确的手机号码")
        } else {
            $.ajax({
                type: "POST",
                url: "/check/check_phone.do",
                data: {
                    validPhone: $("#phone").val()
                },
                success: function(a) {
                    if (a.checkResult == 0) {
                        jQuery("#phone_tip").hide();
                        $("#phone_desc").css("display", "block");
                        jQuery("#phone").parents("li").removeClass("cur_error")
                    } else {
                        if (a.checkResult == 1) {
                            showPhoneError("该手机号已存在，<a href='/passport/login_input.do'>登录</a>")
                        }
                    }
                }
            })
        }
    }
}

function checkPasswordOnBlur(f) {
    hideOtherTips(f);
    var e = check_pwd1(f);
    if (e != 0) {
        jQuery("#" + f + "2").attr("readonly", "readonly")
    }
    if (e == 1) {
        showPassError(f, "密码不能为空")
    } else {
        if (e == 2) {
            showPassError(f, "密码为6-20位字符")
        } else {
            if (e == 3) {
                showPassError(f, "密码为6-20位字符")
            } else {
                if (e == 4) {
                    showPassError(f, "密码中不允许有空格")
                } else {
                    if (e == 5) {
                        showPassError(f, "密码不能全为数字")
                    } else {
                        if (e == 6) {
                            showPassError(f, "密码不能全为字母，请包含至少1个数字或符号 ")
                        } else {
                            if (e == 7) {
                                showPassError(f, "密码不能全为符号")
                            } else {
                                if (e == 8) {
                                    showPassError(f, "密码不能全为相同字符或数字")
                                } else {
                                    var d;
                                    if (f.indexOf("phone") > -1) {
                                        d = {
                                            userName: $("#phone").val(),
                                            password: $("#" + f).val()
                                        }
                                    } else {
                                        d = {
                                            userEmail: $("#email").val(),
                                            password: $("#" + f).val()
                                        }
                                    }
                                    jQuery.ajax({
                                        type: "POST",
                                        url: "/check/check_unsafeInfo.do",
                                        data: d,
                                        success: function(a) {
                                            switch (a.checkResult) {
                                                case 1:
                                                    showPassError(f, "您的密码存在安全隐患,请更改 ");
                                                    break;
                                                case 0:
                                                    jQuery("#" + f + "2").removeAttr("readonly");
                                                    break;
                                                default:
                                                    jQuery("#" + f + "2").removeAttr("readonly");
                                                    break
                                            }
                                        }
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

function checkPassword2OnBlur(c) {
    var d = check_pwd2(c);
    if (d == 1) {
        showPass2Error(c, "确认密码不能为空 ")
    } else {
        if (d == 2) {
            showPass2Error(c, "两次密码输入不一致")
        } else {
            $("#" + c + "2_desc").show();
            jQuery("#" + c + "2").parents("li").removeClass("cur_error")
        }
    }
}

function checkRefererOnBlur() {
    var b = $("#referer").val().replace(/(^ *)|( *$)/g, "");
    if (b != "") {
        $("#refererDesc").load("/check/check_referer.do?userAccount=" + encodeURI($("#referer").val()));
        return false
    }
}

function getPassPoint(d) {
    var e = jQuery(d).val();
    var f = checkPassLength(e);
    f = f + checkPassSymbol(e);
    f = f + checkPassNumAndWord(e);
    f = f + (checkPassAll(e));
    f = f + checkPassAlpha(e);
    f = f + checkPassNum(e);
    return f
}

function checkPassLength(b) {
    if (b.length > 6 && b.length < 8) {
        return 10
    }
    if (b.length >= 8) {
        return 25
    }
    return 0
}

function checkPassSymbol(b) {
    if (getSymbolPattern(2).test(b)) {
        return 25
    } else {
        if (getSymbolPattern(1).test(b)) {
            return 10
        }
    }
    return 0
}

function getSymbolPattern(d) {
    var f = "" + commonSymbol.substr(0, commonSymbol.length - 1) + "{" + d + ",}";
    var e = new RegExp(f);
    return e
}
var patternNumAlpha = /^(?=.*\d.*)(?=.*[a-zA-Z].*)./;

function checkPassNumAndWord(b) {
    if (patternNumAlpha.test(b)) {
        return 5
    }
    return 0
}

function isDigit(c) {
    var d = /(?=.*[0-9])/;
    return getCompareResult(d, c)
}

function isBigWord(c) {
    var d = /(?=.*[A-Z])/;
    return getCompareResult(d, c)
}

function isSymbol(d) {
    var e = "(?=.*" + commonSymbol.substr(0, commonSymbol.length - 1) + ")";
    var f = new RegExp(e);
    return getCompareResult(f, d)
}

function isSmallWord(c) {
    var d = /(?=.*[a-z])/;
    return getCompareResult(d, c)
}

function getCompareResult(d, c) {
    if (d.test(c)) {
        return true
    }
    return false
}

function checkPassAll(b) {
    if (isDigit(b) && isBigWord(b) && isSmallWord(b) && isSymbol(b)) {
        return 5
    }
    if (patternNumAlpha.test(b)) {
        if (isSymbol(b)) {
            return 3
        } else {
            return 2
        }
    }
    return 0
}

function checkPassAlpha(e) {
    var d = /^[a-z]+$|^[A-Z]+$/;
    if (d.test(e)) {
        return 10
    }
    var f = /.*?[A-Z]+?.*?[a-z]+?.*?|.*?[a-z]+?.*[A-Z]+?.*?/;
    if (f.test(e)) {
        return 25
    }
    return 0
}

function checkPassNum(b) {
    if (getNumPattern(3).test(b)) {
        return 20
    }
    if (getNumPattern(1).test(b)) {
        return 10
    }
    return 0
}

function getNumPattern(d) {
    var f = "[0-9]{" + d + ",}";
    var e = new RegExp(f);
    return e
}

function refresh_valid_code(g, e) {
    var h = $("img[name='valid_code_pic']");
    if (h) {
        var f = "/passport/valid_code.do";
        if (valid_code_service_flag == 1) {
            getValidateSigAndSetImageSrc(h, g, e)
        } else {
            h.attr("src", f + "?t=" + Math.random())
        }
    }
}

function refresh_valid_code1() {
    var c = $("#valid_code_pic");
    if (c) {
        var d = "/passport/valid_code.do";
        if (valid_code_service_flag == 1) {
            getValidateSigAndSetImageSrc(c)
        } else {
            c.attr("src", d + "?t=" + Math.random())
        }
    }
}

function getValidateSigAndSetImageSrc(e, d, f) {
    $.ajax({
        type: "GET",
        dataType: "jsonp",
        jsonp: "callback",
        jsonpCallback: "callback123",
        url: "https://captcha.yhd.com/public/getsig.do?t=" + Math.random(),
        success: function(b) {
            var c = b.sig;
            if (typeof f === "function") {
                f.call(d, c)
            } else {
                jQuery("#validateSig").val(c);
                var a = "https://captcha.yhd.com/public/getjpg.do?sig=" + c;
                e.attr("src", a)
            }
        }
    })
}

function checkAccount_beforeFind() {
    if ($("#login_account").val() == "" || $("#login_account").val() == "请输入邮箱地址" || $("#login_account").val() == "请输入手机号/邮箱/用户名") {
        $("#account_desc").text("请输入您的账号");
        $("#login_account").focus();
        $("#account_desc").show();
        return false
    }
    if ($("#vcd").val() == "") {
        $("#vcd").focus();
        $("#vcd_desc").show();
        jQuery("#vcd_desc").parents("li").addClass("cur_error");
        return false
    }
    if ($("#vcd").val().length != 4) {
        $("#vcd").focus();
        $("#vcd_desc").attr("style", "display:inline-block");
        return false
    }
    return true
}

function doEnter() {
    $("#vcd,#login_button").keydown(function(b) {
        b.stopPropagation();
        if (b.keyCode == 13) {
            if (jQuery.browser.msie && jQuery.browser.version == "6.0") {
                double_submit()
            } else {
                jQuery("#login_button").click()
            }
        }
    })
}

function confirmUser() {
    if (!checkAccount_beforeFind()) {
        return false
    }
    var f = {
        account: $("#login_account").val(),
        validCode: $("#vcd").val(),
        sig: jQuery("#validateSig").val()
    };
    var d = "/passport/confirmUserForFindPwd.do";
    var e = "/passport/chooseFindType.do";
    jQuery.post(d, f, function(a) {
        if (a) {
            if (a.errorCode == "00000000") {
                window.location = e
            }
            if (a.errorCode == "00000001") {
                refresh_valid_code1();
                $("#vcd").focus();
                $("#vcd_desc").show();
                jQuery("#vcd_desc").parents("li").addClass("cur_error");
                return
            }
            if (a.errorCode == "00000002") {
                refresh_valid_code1();
                $("#account_desc").text("您输入的账号未找到记录");
                $("#account_desc").show();
                $("#login_account").focus();
                return
            }
            if (a.errorCode == "00000003") {
                refresh_valid_code1();
                $("#account_desc").text("您的账户异常，预计1个工作日内处理完毕");
                $("#account_desc").show();
                $("#login_account").focus();
                return
            }
            if (a.errorCode == "00000004") {
                refresh_valid_code1();
                $("#account_desc").text("未注册邮箱及绑定手机，请致电400-007-1111找回密码");
                $("#account_desc").show();
                $("#login_account").focus();
                return
            }
            if (a.errorCode == "00000012") {
                refresh_valid_code1();
                $("#account_desc").text("您的账号有安全风险已冻结，请致电400-007-1111解冻");
                $("#account_desc").show();
                $("#login_account").focus();
                return
            }
        }
    })
}

function checkRefererLink() {
    var b = location.search;
    if (b.indexOf("rlink") != -1) {
        $("#referer").attr("readonly", "readonly")
    }
}

function checkPassWordContent(c) {
    jQuery("#" + c).parents("li").removeClass("cur_error");
    var d = jQuery("#" + c).val();
    if (d.length > 0) {
        changePassStrong(c)
    } else {
        hideOtherTips(c)
    }
}

function passwordOnFocus(c) {
    var d = jQuery("#" + c);
    if (d.val() == "") {
        hideOtherTips(c);
        return
    }
    checkPassWordContent(c);
    hideOtherTips(c + "2");
    showoff(c + "2_desc")
}

function changePassStrong(c) {
    var d = jQuery("#" + c);
    if (check_pwd1(c) == 0) {
        jQuery("#" + c + "2").removeAttr("readonly");
        jQuery("#" + c + "2").css("background-color", d.css("background-color"))
    } else {
        jQuery("#" + c + "2").attr("readonly", "readonly");
        jQuery("#" + c + "2").css("background-color", "#D2D2D5")
    } if (d.val().length == 0) {
        jQuery("#" + c + "_Level").hide();
        hideOtherTips(c);
        return
    } else {
        jQuery("#" + c + "_tip").hide();
        jQuery("#" + c + "_error").hide()
    }
}

function updatePwdPage() {
    var d = jQuery("#validPhoneCode").val();
    if (d == "" || d.length != 6) {
        $("#validPhoneCode_wrong").show();
        jQuery("#validPhoneCode_wrong").parents("li").addClass("cur_error");
        return
    }
    var f = {
        mobileValidCode: d
    };
    var e = "/passport/validateMobileCheckCode.do";
    jQuery.post(e, f, function(a) {
        if (a) {
            if (a.errorCode == "00000000") {
                window.location = "/passport/updatePwdUseMobileInput.do";
                return
            }
            if (a.errorCode == "00000008") {
                $("#validPhoneCode_wrong").show();
                jQuery("#validPhoneCode_wrong").parents("li").addClass("cur_error");
                return
            }
            if (a.errorCode == "00000009") {
                $("#validPhoneCode_wrong").show();
                jQuery("#validPhoneCode_wrong").parents("li").addClass("cur_error");
                return
            }
            if (a.errorCode == "00000010") {
                $("#validPhoneCode_wrong").show();
                jQuery("#validPhoneCode_wrong").parents("li").addClass("cur_error");
                return
            }
            if (a.errorCode == "00000011") {
                $("#validPhoneCode_wrong").show();
                jQuery("#validPhoneCode_wrong").parents("li").addClass("cur_error");
                return
            }
        }
    })
}

function updatePwdSubmit() {
    var c = check_pwd1("password");
    if (c == 1) {
        showPassError("password", "密码不能为空");
        return false
    }
    if (c == 2) {
        showPassError("password", "密码为6-20位字符");
        return false
    }
    if (c == 3) {
        showPassError("password", "密码为6-20位字符");
        return false
    }
    if (c == 4) {
        showPassError("password", "密码中不允许有空格");
        return false
    }
    if (c == 5) {
        showPassError("password", "密码不能全为数字");
        return false
    }
    if (c == 6) {
        showPassError("password", "密码不能全为字母，请包含至少1个数字或符号 ");
        return false
    }
    if (c == 7) {
        showPassError("password", "密码不能全为符号");
        return false
    }
    if (c == 8) {
        showPassError("password", "密码不能全为相同字符或数字");
        return false
    }
    var d = check_pwd2("password");
    if (d == 1) {
        showPass2Error("password", "确认密码不能为空");
        return false
    } else {
        if (d == 2) {
            showPass2Error("password", "两次密码输入不一致");
            return false
        }
    }
    return true
};
var clickFlag = false;
var nowid;
var totalid;
var can1press = false;
var emailafter;
var emailbefor;
var isShow = true;
var isRed = true;
var showCodeFlag = false;

function isEmail(a) {
    if (a.indexOf("@") > 0) {
        return true
    } else {
        return false
    }
}

function doSubmit(a) {
    if (doSubmitPwd(a) == false) {
        return false
    }
    var b = check_email();
    if (b == 1) {
        showEmailError("不能为空");
        return false
    } else {
        if (b == 2) {
            showEmailError("");
            $("#email_error").html("格式错误，请输入正确的邮箱");
            return false
        } else {
            if (b == 3) {
                showEmailError("");
                $("#email_error").html("邮箱长度不能超过100位字符");
                return false
            } else {
                if (b == 4) {
                    showEmailError("");
                    $("#email_error").html("雅虎中国邮箱已停用，请更换邮箱注册");
                    return false
                } else {
                    if ($("#email_desc").html() == "重复的email") {
                        $("#email").focus();
                        return false
                    }
                }
            }
        }
    }
    return true
}

function doPhoneSubmit(b) {
    if (doSubmitPwd(b) == false) {
        return false
    }
    var a = check_phone();
    if (a == 1) {
        showPhoneError("不能为空");
        return false
    } else {
        if (a == 2) {
            showPhoneError("格式错误，请输入正确的手机号码");
            return false
        }
    }
    return true
}

function doSubmitPwd(a) {
    var b = check_pwd1(a);
    if (b == 1) {
        showPassError(a, "密码不能为空");
        return false
    }
    if (b == 2) {
        showPassError(a, "密码为6-20位字符");
        return false
    }
    if (b == 3) {
        showPassError(a, "密码为6-20位字符");
        return false
    }
    if (b == 4) {
        showPassError(a, "密码中不允许有空格");
        return false
    }
    if (b == 5) {
        showPassError(a, "密码不能全为数字");
        return false
    }
    if (b == 6) {
        showPassError(a, "密码不能全为字母，请包含至少1个数字或符号 ");
        return false
    }
    if (b == 7) {
        showPassError(a, "密码不能全为符号");
        return false
    }
    if (b == 8) {
        showPassError(a, "密码不能全为相同字符或数字");
        return false
    }
    var c = check_pwd2(a);
    if (c == 1) {
        showPass2Error(a, "确认密码不能为空");
        return false
    } else {
        if (c == 2) {
            showPass2Error(a, "两次密码输入不一致");
            return false
        }
    }
};
Tips = function(b, c) {
    var a = true;

    function d() {
        if (a) {
            a = false;
            b.html(c);
            b.addClass("show");
            setTimeout(function() {
                a = true;
                b.removeClass("show")
            }, 2000)
        }
    }
    return {
        show: d
    }
};