(ns clay-rt.example
  (:require [std.lang :as l]
            [std.lib :as h]
            [lib.redis.bench :as bench]
            [kmi.redis :as redis]))

(bench/start-redis-array [17001])

(l/script- :lua
  {:runtime :redis.client
   :config {:port 17001}
   :require [[kmi.redis :as redis]]})

(defn.lua add-10
  [x]
  (return
   (+ x 10)))


(!.lua
  (redis/time-us))

^*(!.lua
    (+ (redis/time-ms)
       (redis/time-us)
       (redis/time-ms)
       (-/add-10 (+ 1 2 3))))

(l/with:print-all
  (-/add-10 (+ 1 2 3)))


(l/script :js)
(l/script :python)
(l/script :r)


(comment
  (!.lua
    (fn []
      (return (+ 1 2 3))))
  
  )

(comment
  [(!.lua
    (fn []
      (return (+ 1 2 3))))
   
   (!.js
    (fn []
      (return (+ 1 2 3))))

   (!.R
    (fn []
      (return (+ 1 2 3))))
   
   (!.py
    (fn []
      (return (+ 1 2 3))))]
  ["function ()\n  return 1 + 2 + 3\nend"
   "function (){\n  return 1 + 2 + 3;\n}"
   "function (){\n  return(1 + 2 + 3);\n}"
   "(lambda : 1 + 2 + 3)"])

