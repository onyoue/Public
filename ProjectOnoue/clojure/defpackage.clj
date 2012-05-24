; Problem 1

(defn sum [a]
  (if (empty? a) 0
    (+ (first a) (sum (rest a)))))

(defn range [a b]
  (if (> a b)
    nil
    (cons a (range (+ a 1) b))))

;(sum (filter (fn [x] (or (== (mod x 3) 0) (== (mod x 5) 0))) (range 1 999)))

; Problem 2
(defn fib0 [n]
  (if (= n 1) 1
    (if (= n 2) 2
      (+ (fib0 (- n 1))
        (fib0 (- n 2))))))

(defn fib2 [n1 n2 max]
  (let [v (+ n1 n2)]
    (if (> v max) nil
      (cons v (fib2 v n1 max)))))

(defn fib [max]
  (concat [1 2] (fib2 2 1 max)))

;(sum (filter (fn [x] (even? x)) (fib 4000000)))

; Problem 3

;(defn test-seq [f a]
;   (if (empty? a) false
;     (if (f (first a)) true
;       (test-seq f (rest a)))))
;
;(test-seq (fn [x] (even? x)) '(1 3 4))

(use '[clojure.contrib.lazy-seqs :only (primes)])
;(take 5 (drop-while (fn [x] (< x 10000)) primes))

; 600851475143

;(defn lpm [n tmp prms]
;  (let [a (first prms)]
;    (if (> a n) tmp
;      (if (== 0 (mod n a))
;        (lpm n a (rest prms))
;        (lpm n tmp (rest prms))))))

(defn lpm [n tmp prms]
  (let [a (first prms)]
    (if (> a n) tmp
      (if (== 0 (mod n a))
        (lpm (quot n a) a (rest prms))
        (lpm n tmp (rest prms))))))


(defn l-p-m [n]
  (lpm n 1 primes))
;  (lpm (quot n 2) 1 primes))
;(l-p-m 600851475143)

; Problem 4

(defn to-num-list [n a]
  (if (== n 0) a
    (let [nexta (cons (mod n 10) a)]
      (to-num-list (quot n 10) nexta))))

(defn palindrome2? [a b]
  (if (and (empty? a) (empty? b)) true
    (if (false? (== (first a) (first b))) false
      (palindrome2? (rest a) (rest b)))))

(defn palindrome? [n]
  (let [nl (to-num-list n [])] (palindrome2? nl (reverse nl))))

(defn palin-list2 [n r2]
  (if (empty? r2)
    nil
    (let [nr2 (* n (first r2))]
      (if (palindrome? nr2)
        (cons nr2 (palin-list2 n (rest r2)))
        (palin-list2 n (rest r2))))))

(defn palin-list [r1 r2]
  (if (empty? r1)
    nil
    (into (palin-list2 (first r1) r2)
      (palin-list (rest r1) r2))))

(defn mymax [a tmp-max]
  (if (empty? a) tmp-max
    (let [aa (first a)]
      (if (> aa tmp-max)
        (mymax (rest a) aa)
        (mymax (rest a) tmp-max)))))


;(mymax (palin-list (range 10 99) (range 10 99)) 0)
;(mymax (palin-list (range 100 999) (range 100 999)) 0)

; Problem 5

(defn warikireru? [n a]
  (if (empty? a) true
    (let [aa (first a)]
      (if (== 0 (mod n aa))
        (warikireru? n (rest a))
        false))))

(defn min-warikireru [n a]
  (if (warikireru? n a) n
    (min-warikireru (inc n) a)))

(defn kakeru [a]
  (if (empty? a) 1
    (* (first a) (kakeru (rest a)))))

;(min-warikireru 1 (range 1 10))
;(min-warikireru 2 (range 1 20))
;(min-warikireru 2 '(20 19 18 17 16 15 14 13 12 11))

;(kakeru (take-while (fn [x] (< x 20)) primes))
;(kakeru (concat '(20 18 16 14 15 12) (take-while (fn [x] (< x 20)) primes)))

(defn kouyakufilter [a p]
  (if (empty? a) nil
    (let [n (first a)]
      (if (== 0 (mod n p))
        (cons (quot n p)
          (kouyakufilter (rest a) p))
        (cons n
          (kouyakufilter (rest a) p))))))

(defn kouyaku? [a p n]
  (if (> n 1) true
    (if (empty? a) false
      (let [aa (first a)]
        (if (== 0 (mod aa p))
          (kouyaku? (rest a) p (inc n))
          (kouyaku? (rest a) p n))))))

(defn minkoubai [a prs results]
  (if (empty? prs)
    (kakeru (concat a results))
    (let [p (first prs)]
      (if (kouyaku? a p 0)
        (minkoubai (kouyakufilter a p) (take-while (fn [x] (< x 20)) primes) (cons p results))
        (minkoubai a (rest prs) results)))))

;(minkoubai (range 2 20) (take-while (fn [x] (< x 20)) primes) [])

; Problem 6

;(def s100 (sum (range 1 100)))
;(def ss100 (sum (map (fn [x] (* x x)) (range 1 100))))
;(- (* s100 s100) ss100)

; Problem 7
;(nth primes 10000)

; Problem 8

(def big-str (str "73167176531330624919225119674426574742355349194934
96983520312774506326239578318016984801869478851843
85861560789112949495459501737958331952853208805511
12540698747158523863050715693290963295227443043557
66896648950445244523161731856403098711121722383113
62229893423380308135336276614282806444486645238749
30358907296290491560440772390713810515859307960866
70172427121883998797908792274921901699720888093776
65727333001053367881220235421809751254540594752243
52584907711670556013604839586446706324415722155397
53697817977846174064955149290862569321978468622482
83972241375657056057490261407972968652414535100474
82166370484403199890008895243450658541227588666881
16427171479924442928230863465674813919123162824586
17866458359124566529476545682848912883142607690042
24219022671055626321111109370544217506941658960408
07198403850962455444362981230987879927244284909188
84580156166097919133875499200524063689912560717606
05886116467109405077541002256983155200055935729725
71636269561882670428252483600823257530420752963450"))

(def big-digit (map #(Integer. (str %)) (filter #(Character/isDigit %) (seq big-str))))
;(apply max (map #(reduce * %)
;  (for [index (range 0 (count big-digit))]
;      (take 5 (drop index big-digit)))))


; Problem 9

;(for [a (range 1000) b (range 1000) c (range 1000) :when (and (and (> c a) (> c b) (> b a)) (== (+ a b c) 1000) (== (* c c) (+ (* a a) (* b b))))]
;  (* a b c))


; Problem 10
;(reduce + (take-while #(< % 10) primes))
;(reduce + (take-while #(< % 2000000) primes))


; Problem 20

(defn fact0 [result n]
  (if (< n 1) result
    (recur (* n result) (- n 1))))

(defn fact [n]
  (fact0 1 n))

;(reduce + (to-num-list (fact 100) []))


; Problem 16

(use 'clojure.contrib.math)
;(reduce + (to-num-list (expt 2 1000) []))

; Problem 12

(use '[clojure.contrib.def :only (defvar)])
(defvar count-if (comp count filter) "Count items matching a filter")

(defn lazy-triangle-numbers
  [sum n]
  (lazy-seq
    (cons sum (lazy-triangle-numbers (+ sum n) (inc n)))))

(def triangle-numbers (lazy-triangle-numbers 1 2))

;(defn count-divisor [input]
;  (count-if #(= 0 (mod input %)) (range 1 input)))

(defn find-min-divisor [input]
  (letfn [(fmd [count input]
            (if (= 0 (mod input count))
              count
              (fmd (inc count) input)))]
    (fmd 2 input)))

(defn count-divisor [input]
  (letfn [(count-divisor-1 [result input]
            (let [min-div (find-min-divisor input)]
              (if (= min-div input)
                result
                (count-divisor-1 (+ result 2) (/ input min-div)))))]
    (count-divisor-1 2 input)))

(defn count-divisor-slow [input]
  (letfn [(count-divisor-1 [result count input]
            (if (< count 1)
              result
              (if (= 0 (mod input count))
                (recur (inc result) (dec count) input)
                (recur result (dec count) input))))]
    (+ 1 (count-divisor-1 0 (/ input 2) input))))

(defn find-triangle-number
  [min-divisor-count]
  (letfn [(ftn [min-count tns]
            (let [cn (count-divisor (first tns))]
              (if (>= cn min-count)
                (first tns)
                (recur min-count (rest tns)))))]
    (ftn min-divisor-count triangle-numbers)))

;(find-triangle-number 5)
;(find-triangle-number 500)
