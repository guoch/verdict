/*
 * Copyright 2017 University of Michigan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.umich.verdict.relation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;

import edu.umich.verdict.datatypes.TableUniqueName;
import edu.umich.verdict.relation.expr.ColNameExpr;
import edu.umich.verdict.relation.expr.Expr;
import edu.umich.verdict.relation.expr.FuncExpr;
import edu.umich.verdict.relation.expr.TableNameExpr;

public class NoApproxRelation extends ApproxRelation {

    ExactRelation r;

    public NoApproxRelation(ExactRelation r) {
        super(r.vc);
        this.r = r;
    }

    @Override
    public ExactRelation rewriteWithSubsampledErrorBounds() {
        return r;
    }

    @Override
    public ExactRelation rewriteForPointEstimate() {
        return r;
    }

    @Override
    protected ExactRelation rewriteWithPartition() {
        return r;
    }

    @Override
    protected List<Expr> samplingProbabilityExprsFor(FuncExpr f) {
        return Arrays.asList();
    }

    @Override
    public String sampleType() {
        return "nosample";
    }

    @Override
    public double cost() {
        return 1e6; // TODO: a better alternative?
    }

    @Override
    protected List<String> getColumnsOnWhichSamplesAreCreated() {
        return new ArrayList<String>();
    }

    @Override
    protected Map<TableUniqueName, String> tableSubstitution() {
        return ImmutableMap.of();
    }

    @Override
    protected String toStringWithIndent(String indent) {
        StringBuilder s = new StringBuilder(1000);
        s.append(indent);
        s.append(String.format("%s(%s)\n", this.getClass().getSimpleName(), getAlias()));
        s.append(r.toStringWithIndent(indent + "  "));
        return s.toString();
    }

    @Override
    public boolean equals(ApproxRelation o) {
        if (o instanceof NoApproxRelation) {
            return r.equals(((NoApproxRelation) o).r);
        } else {
            return false;
        }
    }

    @Override
    public double samplingProbability() {
        return 1.0;
    }

    @Override
    protected boolean doesIncludeSample() {
        return false;
    }

    @Override
    public Expr tupleProbabilityColumn() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Expr tableSamplingRatio() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ColNameExpr> getAssociatedColumnNames(TableNameExpr tabExpr) {
        return new ArrayList<ColNameExpr>();
    }
}
