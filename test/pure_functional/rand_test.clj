(ns pure-functional.rand-test
  (:require [clojure.test :refer :all]
            [pure-functional.rand :refer :all]))

(declare isMonotonic)
(declare chi-square)

(deftest linear-congruential-test
  (testing "should return one rand number not equal to seed"
    (is (not= [3] (take 1 (linear-congruential)))))
  (testing "should return many number not equal to each other"
    (is (= 10 (let [result (take 10 (linear-congruential))]
                (count (set result))))))
  (testing "should not have numbers in monotonic order"
    (is (not (isMonotonic (take 10 (linear-congruential))))))
  (testing "should chi-square be lower then upperBound+2*upperBound^0.5"
    (is 
      (>
        (+ 100 (* 2 (Math/pow 100 0.5)))
        (let [upperBound 100
              N 1000
              randNumbers (take N (linear-congruential upperBound))]
          (Math/abs (chi-square randNumbers N upperBound))
        )))))

(deftest *mod48-test
  (testing "should a*b if a*b =< 2^48"
    (is (= (* 4 5) (*mod48 4 5))))
  (testing "should a*b mod 2^48 if a*b > 2^48"
    (is (= 16777216 (*mod48 (.longValue (Math/pow 2 24)) (+ 1 (.longValue (Math/pow 2 24))))))))

(defn chi-square
  [randNumbers N upperBound]
  (let [freqBucket (reduce #(assoc %1 %2 (inc (%1 %2)))  (vec (repeat upperBound 0)) randNumbers)
        desireFreq (/ N upperBound)
        squareError (reduce #(+ %1 (Math/pow (- desireFreq %2) 2)) 0 freqBucket)
        chiSquareVal (/ squareError desireFreq)]
          chiSquareVal))

(defn isMonotonic
  [v]
  (= (count v) (+ 1 (Math/abs (second (reduce 
    #(if (nil? (first %1))
      [%2 0]
      (if (< (first %1) %2) 
        [%2 (inc (second %1))] 
        [%2 (dec (second %1))])) 
    [nil  0] v))))))
