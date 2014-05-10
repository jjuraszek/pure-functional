(ns pure-functional.rand-test
  (:require [clojure.test :refer :all]
            [pure-functional.rand :refer :all]))

(declare isMonotonic)

(deftest linear-congruential-test
  (testing "should return one rand number not equal to seed"
    (is (not= [3] (take 1 (linear-congruential)))))
  (testing "should return many number not equal to each other"
    (is (= 10 (let [result (take 10 (linear-congruential))]
                (count (set result))))))
  (testing "should not have numbers in monotonic order"
    (is (not (isMonotonic (take 10 (linear-congruential)))))))

(deftest *mod32-test
  (testing "should a*b if a*b =< 2^32"
    (is (= (* 4 5) (*mod32 4 5))))
  (testing "should a*b mod 2^32 if a*b > 2^32"
    (is (= 65536 (*mod32 (.intValue (Math/pow 2 16)) (+ 1 (.intValue (Math/pow 2 16))))))))

(defn isMonotonic
  [v]
  (= (count v) (+ 1 (Math/abs (second (reduce 
    #(if (nil? (first %1))
      [%2 0]
      (if (< (first %1) %2) 
        [%2 (inc (second %1))] 
        [%2 (dec (second %1))])) 
    [nil  0] v))))))
