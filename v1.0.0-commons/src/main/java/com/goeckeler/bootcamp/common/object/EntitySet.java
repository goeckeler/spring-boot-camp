package com.goeckeler.bootcamp.common.object;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Supports a set to be an object.
 * 
 * @param <T> the entity type contained in the set.
 */
public abstract class EntitySet<T>
  implements Serializable
{
  private static final long serialVersionUID = 75329566937972162L;

  public EntitySet() {
    super();
  }

  protected abstract Set<T> data();

  protected abstract void data(final Set<T> set);

  /**
   * Use for write access to the set of artists.
   * 
   * @return the list of artists, never <code>null</code>
   */
  private Set<T> setItems() {
    if (data() == null) {
      data(new TreeSet<>());
    }
    return data();
  }

  /**
   * Read-only list of all artists.
   * 
   * @return the current list of artists, never <code>null</code>
   */
  public final Set<T> getItems() {
    return SetUtils.unmodifiableSet(SetUtils.emptyIfNull(data()));
  }

  public final int size() {
    return getItems().size();
  }

  public final boolean add(T item) {
    return setItems().add(item);
  }

  public final boolean remove(T item) {
    return setItems().remove(item);
  }

  public final boolean contains(T item) {
    return setItems().contains(item);
  }

  public final void clear() {
    setItems().clear();
  }

  public final boolean addAll(final Collection<T> collection) {
    return setItems().addAll(collection);
  }

  public final void forAll(final Iterable<T> collection) {
    this.clear();
    collection.forEach(item -> this.add(item));
  }

  @Override
  public String toString() {
    return StringUtils.join(setItems(), delimiter());
  }

  protected String delimiter() {
    return ", ";
  }
}
