(ns pure-functional.rand-test
  (:require [clojure.test :refer :all]
            [pure-functional.rand :refer :all]))

(declare isMonotonic)

(deftest linear-congruential-test
  (testing "should return one rand number not equal to seed"
    (is (not= [3] ((linear-congruential 3) 1))))
  (testing "should return many number not equal to each other"
    (is (= 10 (let [result ((linear-congruential 3) 10)]
                (count (set result))))))
  (testing "should not have numbers in monotonic order"
    (is (not (isMonotonic ((linear-congruential 3) 10))))))

(defn isMonotonic
  [v]
  (= (count v) (+ 1 (Math/abs (second (reduce 
    #(if (nil? (first %1))
      [%2 0]
      (if (< (first %1) %2) 
        [%2 (inc (second %1))] 
        [%2 (dec (second %1))])) 
    [nil  0] v))))))
