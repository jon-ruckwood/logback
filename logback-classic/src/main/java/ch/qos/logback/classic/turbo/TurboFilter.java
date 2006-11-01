package ch.qos.logback.classic.turbo;

import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;

/**
 * TurboFilter is a specialized filter with a decide method that takes a bunch of parameters
 * instead of a single event object. The latter is cleaner but the latter is much more 
 * performant.
 * 
 * @author Ceki Gulcu
 */
public abstract class TurboFilter extends ContextAwareBase implements LifeCycle {

  private String name;
  boolean start = false;
  
  /**
   * Points to the next filter in the filter chain.
   */
  private TurboFilter classicNext;

  /**
   * Make a decision based on the multiple parameters passed as arguments.
   * The returned value should be one of <code>{@link Filter#DENY}</code>, 
   * <code>{@link Filter#NEUTRAL}</code>, or <code>{@link Filter#ACCEPT}</code>.
  
   * @param marker
   * @param logger
   * @param level
   * @param format
   * @param params
   * @param t
   * @return
   */
  public abstract int decide(Marker marker, Logger logger,
      Level level, String format, Object[] params, Throwable t);

  public void start() {
    this.start = true;
  }
  
  public boolean isStarted() {
    return this.start;
  }

  public void stop() {
    this.start = false;
  }
  
  
  /**
   * Set the next filter pointer.
   */
  public void setNext(TurboFilter next) {
    this.classicNext = next;
  }

  /**
   * Return the pointer to the next filter;
   */
  public TurboFilter getNext() {
    return classicNext;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}