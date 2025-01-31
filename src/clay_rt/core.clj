(ns clay-rt.core
  (:require [scicloj.kindly.v4.kind :as kind]
            [std.lang :as l]
            [std.lib :as h]
            [std.html :as html]))

;; creates a python runtime
(l/script
 :python
 {:runtime :basic
  :import [[matplotlib.pyplot :as plt]]})

(def +svg+
  (!.py
   (:- :import io)
   (:- :import base64)
   (:- :import matplotlib.pyplot :as plt)
   (var xs [1 2 3 4 5])
   (var ys [2 4 1 8 6])
   (plt.plot xs ys
             :marker "o"
             :linestyle "-"
             :color "b"
             :label "Sample")
   (var buf (io.StringIO))
   (plt.savefig buf :format "svg")
   (plt.close)
     

   (var svg-str (buf.getvalue))
   svg-str))

;; Display Graph
(kind/html +svg+)

;; Hiccup
(kind/hiccup (html/tree +svg+))

(kind/hiccup
 [:script {:type "module"}
  (!.js (:- :import shuffle :from
            "'https://unpkg.com/lodash-es@4.17.21/shuffle.js'")
        (var nums [1 2 3 4 5])
        (console.log (shuffle nums)))])

;; creates a bash runtime
(l/script
 :bash
 {:runtime :oneshot})

(!.sh
 (echo "hello"))

;; creates an r runtime
(l/script
 :r
 {:runtime :basic})

(!.R
 (+ 1 2 3))

;; creates a js runtime
(l/script
 :js
 {:runtime :basic
  })

(require '[std.lang :as l]
         '[std.lib :as h])

(defn js-template
  [& forms]
  (h/$ (return
        ((:- \(
             (fn []
               (return ~@forms))
             \))))))

(template '(+ 1 2 3))

(defn.js add-10
  [x]
  (return (+ 1 2 3)))

(kind/html 
 (!.js
  (var D3Node (require "d3-node"))
  (var d3n (new D3Node.D3Node))
  (var d3 d3n.d3)
  (var svg (d3n.createSVG 400 400))
  (. svg
     (append "text")
     (text "Hello D3 in Node")
     (attr "x" 50)
     (attr "y" 50)
     (attr "font-size" "24px")
     (attr "fill" "blue"))

  (var svg-str (d3n.svgString))
  svg-str))

