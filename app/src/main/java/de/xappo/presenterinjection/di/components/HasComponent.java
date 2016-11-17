package de.xappo.presenterinjection.di.components;

public interface HasComponent<T> {

    T getComponent();

    void setComponent(T component);

}
