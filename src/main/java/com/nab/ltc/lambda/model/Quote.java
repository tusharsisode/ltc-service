
package com.nab.ltc.lambda.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "time",
    "price"
})
public class Quote implements Serializable
{

    @JsonProperty("time")
    private String time;
    @JsonProperty("price")
    private String price;
    private final static long serialVersionUID = 9127062726929957770L;

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("time", time).append("price", price).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(time).append(price).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Quote) == false) {
            return false;
        }
        Quote rhs = ((Quote) other);
        return new EqualsBuilder().append(time, rhs.time).append(price, rhs.price).isEquals();
    }

}
