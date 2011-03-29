
(let [[x & xs] [1 2 3]]
     (list x xs)) ; => (1 (2 3))

(defn remove-first2 [e coll]
 (if (empty? coll)
   '()
   (let [[x & coll*] coll]
   (if (= e x)
     coll*
     (cons x (remove-first2 e coll*))))))

(defn remove-first3 [e coll]
  (when-let [[x & coll*] coll]
    (if (= e x) coll* (cons x (remove-first3 e coll*)))))

(defn remove-first* [e coll]
  (lazy-seq
   (when-let [[x & xs] coll]
     (if (= e x) xs (cons x (remove-first* e xs))))))

(defn remove-first+ [e coll]
  (let [[left right] (split-with #(not (= e %)) coll)]
    (lazy-cat left (next right))))

(let [xs (range 1 100000)]
  ;;(time (dotimes [i 1000] (remove-first3 99999 xs))) ;;‚à‚¿‚ë‚ñStackOverflow
    (time (dotimes [i 1000] (remove-first+ 99999 xs)))
    (time (dotimes [i 1000] (remove-first* 99999 xs))))

;; "Elapsed time: 4.098972 msecs"
;; "Elapsed time: 0.818267 msecs"

(defn split-with-cont [p coll cont]
  (lazy-seq
   (when-let [[x & xs] coll]
     (if (not (p x)) (cont coll) (cons x (split-with-cont p xs cont))))))

(defn remove-first+2 [e coll]
  (split-with-cont #(not (= e %)) coll next))