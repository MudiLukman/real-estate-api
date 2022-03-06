package domain.specifications;

public interface AbstractSpecification<T> {
    public boolean isSatisfiedBy(T t);
}
