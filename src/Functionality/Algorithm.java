/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functionality;

/**
 *
 * @author Luna
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import model.Monster;
import model.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Algorithm {
    
//find
    public static <T> T find(Iterator<T> iterator, T value) {
        Predicate<T> pred = value::equals;
        return find(iterator, pred);
    }
    public static <T> T find(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }
    public static <T> T find(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, pred);
    }
    public static <T> T find(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return find(it, value);
    }
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current)) return current;
        }
        return null;
    }
    
//Collect
    public static <T> List<T> collect(Iterator<T> iterator, T value) {
        Predicate<T> pred = value::equals;
        return collect(iterator, pred);
    }
    public static <T> List<T> collect(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, value);
    }
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return collect(it, pred);
    }
    public static <T> List<T> collect(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, pred);
    }
    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return collect(it, value);
    }
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred) {
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current)) list.add(current);
        }
        return list;
    }
    
//convert to desired Class    
    public static <T extends Pokemon> List<T> toDifferentClass(List<Pokemon> pks, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        pks.forEach(p -> list.add(clazz.cast(p)));
        return list;
    }
    
}

