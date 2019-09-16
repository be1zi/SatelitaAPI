package Satelita.Network.Error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class GraphQLErrorAdapter implements GraphQLError {

    private final GraphQLError error;

    public GraphQLErrorAdapter(GraphQLError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return (error instanceof ExceptionWhileDataFetching) ? ((ExceptionWhileDataFetching) error).getException().getMessage() : error.getMessage();
    }

    @Override
    @JsonIgnore
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    @JsonIgnore
    public ErrorType getErrorType() {
        return null;
    }

    @Override
    @JsonIgnore
    public List<Object> getPath() {
        return null;
    }

    @Override
    @JsonIgnore
    public Map<String, Object> toSpecification() {
        return null;
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getExtensions() {
        return null;
    }
}
