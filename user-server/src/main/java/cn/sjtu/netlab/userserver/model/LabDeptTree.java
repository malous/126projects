package cn.sjtu.netlab.userserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabDeptTree {
    private BigInteger id;
    private String name;
    private List<LabDeptTree> children;

    public void addChild (LabDeptTree deptTree) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(deptTree);
    }
}
